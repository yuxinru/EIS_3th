package com.trader.handler;

import com.alibaba.fastjson.JSONObject;
import com.trader.entity.Order;
import com.trader.parameter.MyOrder;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ScheduledMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;


@Component
@Slf4j
public class ActiveMQHandler {

    private final JmsMessagingTemplate broker1ActivemqTemplate;

    private final JmsMessagingTemplate broker2ActivemqTemplate;

    @Autowired
    public ActiveMQHandler(JmsMessagingTemplate broker1ActivemqTemplate, JmsMessagingTemplate broker2ActivemqTemplate) {
        this.broker1ActivemqTemplate = broker1ActivemqTemplate;
        this.broker2ActivemqTemplate = broker2ActivemqTemplate;
    }

    public void send1(String queueName, Order order) {
        log.info(">>>>>>>立即发送:" + order.toString());

        broker1ActivemqTemplate.convertAndSend(queueName, JSONObject.toJSONString(order));
        //template.convertAndSend(queueName, data);
    }
    public void send2(String queueName, Order order) {
        log.info(">>>>>>>立即发送:" + order.toString());

        broker2ActivemqTemplate.convertAndSend(queueName, JSONObject.toJSONString(order));
        //template.convertAndSend(queueName, data);
    }
    public void send1(String queueName, String username, int productId) {
        MyOrder myOrder = new MyOrder(username, productId, 2);
        log.info(">>>>>>>立即发送:" + myOrder.toString());

        broker1ActivemqTemplate.convertAndSend(queueName, JSONObject.toJSONString(myOrder));
    }
    public void send2(String queueName, String username, int productId) {
        MyOrder myOrder = new MyOrder(username, productId, 2);
        log.info(">>>>>>>立即发送:" + myOrder.toString());

        broker2ActivemqTemplate.convertAndSend(queueName, JSONObject.toJSONString(myOrder));
    }
//    /**
//     * 延时发送的信息
//     * @param name 监听的名称
//     * @param data 发送的数据
//     * @param time 延时多少时间处理消息.
//     */
//    public void delaySend(String name, String data, long time) {
//        log.info("====>>> 延时任务:" + name + ",data:" + data);
//        //获取连接工厂
//        ConnectionFactory connectionFactory = template.getConnectionFactory();
//        try {
//            //获取连接
//            assert connectionFactory != null;
//            Connection connection = connectionFactory.createConnection();
//            connection.start();
//            //获取session, true开启事务，false关闭事务
//            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
//            // 创建一个消息队列
//            Destination destination = session.createQueue(name);
//            MessageProducer producer = session.createProducer(destination);
//            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
//            TextMessage message = session.createTextMessage(data);
//            //设置延迟时间 //AMQ_SCHEDULED_DELAY
//            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time * 1000L);
//            //发送
//            producer.send(message);
//            session.commit();
//            producer.close();
//            session.close();
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

