package com.robot.handler;

import com.alibaba.fastjson.JSONObject;
import com.robot.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MQHandler {
    private final JmsMessagingTemplate template;

    @Autowired
    public MQHandler(JmsMessagingTemplate template) {
        this.template = template;
    }

    public void send(String queueName, Order order) {
        System.out.println(">>>>>>>立即发送:" + order.toString());

        template.convertAndSend(queueName, JSONObject.toJSONString(order));
        //template.convertAndSend(queueName, data);
    }
}
