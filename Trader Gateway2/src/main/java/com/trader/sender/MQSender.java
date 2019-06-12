package com.trader.sender;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.annotation.Resource;
import javax.jms.Queue;

public class MQSender {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private Queue queue;

    public void send(){
        //Destination destination = new ActiveMQQueue("queue01");
        jmsMessagingTemplate.convertAndSend("queue01", "testtesttesttest");
        //jmsMessagingTemplate.convertAndSend(new ActiveMQQueue("queue01"), "testtesttesttest");
    }
}
