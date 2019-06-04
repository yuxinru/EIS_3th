package com.broker.handler;

import com.alibaba.fastjson.JSONObject;
import com.broker.entity.BuyMarketDepth;
import com.broker.entity.Orderblotter;
import com.broker.entity.SellMarketDepth;
import com.trader.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        log.info(">>>>>>>立即发送:" + data);

        template.convertAndSend(queueName, data);
    }
    public void send(String queueName, LinkedList<Orderblotter> orderblotter) {
        log.info(">>>>>>>立即发送:" + orderblotter.toString());

        template.convertAndSend(queueName, JSONObject.toJSONString(orderblotter));
        //template.convertAndSend(queueName, data);
    }
    public void send(String queueName, SellMarketDepth sellMarketDepth) {
        log.info(">>>>>>>立即发送:" + sellMarketDepth.toString());

        template.convertAndSend(queueName, JSONObject.toJSONString(sellMarketDepth));
        //template.convertAndSend(queueName, data);
    }
    public void send(String queueName, BuyMarketDepth buyMarketDepth) {
        log.info(">>>>>>>立即发送:" + buyMarketDepth.toString());

        template.convertAndSend(queueName, JSONObject.toJSONString(buyMarketDepth));
        //template.convertAndSend(queueName, data);
    }
}
