package com.trader.handler;

import com.alibaba.fastjson.JSONObject;
import com.trader.entity.Order;
import org.apache.activemq.ScheduledMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;


@Component
public class ActiveMQHandler {

    private final JmsMessagingTemplate template;

    @Autowired
    public ActiveMQHandler(JmsMessagingTemplate template) {
        this.template = template;
    }

    /**
     * 发送即时消息
     * @param queueName 队列名
     * @param data 数据
     */
    public void send(String queueName, String data) {
        System.out.println(">>>>>>>立即发送:" + data);

        template.convertAndSend(queueName, data);
    }
    public void send(String queueName, Order order) {
        System.out.println(">>>>>>>立即发送:" + order.toString());

        template.convertAndSend(queueName, JSONObject.toJSONString(order));
        //template.convertAndSend(queueName, data);
    }

    /**
     * 延时发送的信息
     * @param name 监听的名称
     * @param data 发送的数据
     * @param time 延时多少时间处理消息.
     */
    public void delaySend(String name, String data, long time) {
        System.out.println("====>>> 延时任务:" + name + ",data:" + data);
        //获取连接工厂
        ConnectionFactory connectionFactory = template.getConnectionFactory();
        try {
            //获取连接
            assert connectionFactory != null;
            Connection connection = connectionFactory.createConnection();
            connection.start();
            //获取session, true开启事务，false关闭事务
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
            Destination destination = session.createQueue(name);
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            TextMessage message = session.createTextMessage(data);
            //设置延迟时间 //AMQ_SCHEDULED_DELAY
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time * 1000L);
            //发送
            producer.send(message);
            session.commit();
            producer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

