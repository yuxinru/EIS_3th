package com.broker.handler;

import com.alibaba.fastjson.JSONObject;
import com.broker.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Component
@Slf4j
public class ActiveMQHandler {
    @Resource
    private JmsMessagingTemplate template;

    /**
     * 发送即时消息
     * @param queueName 队列名
     * @param data 数据
     */
    public void send(String queueName, String data) {
        //log.info(">>>>>>>立即发送:" + data);

        template.convertAndSend(queueName, data);
    }
    public void send(String queueName, OrderBlotters orderBlotters) {
        //log.info(">>>>>>>立即发送:" + orderBlotters.toString());

        template.convertAndSend(queueName, JSONObject.toJSONString(orderBlotters));
        //template.convertAndSend(queueName, data);
    }
    public void send(String queueName, MarketDepths marketDepths) {
        //log.info(">>>>>>>立即发送:" + marketDepths.toString());

        template.convertAndSend(queueName, JSONObject.toJSONString(marketDepths));
        //template.convertAndSend(queueName, data);
    }
    public void send(String queueName, Orders orders) {
        log.info(">>>>>>>立即发送:" + orders.toString());

        template.convertAndSend(queueName, JSONObject.toJSONString(orders));
    }
}
