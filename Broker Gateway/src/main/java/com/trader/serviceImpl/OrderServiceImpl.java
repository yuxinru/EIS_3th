package com.trader.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.trader.entity.BuyMarketDepth;
import com.trader.entity.OrderBlotter;
import com.trader.entity.Order;
import com.trader.handler.ActiveMQHandler;
import com.trader.service.OrderService;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import javax.jms.*;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    @Resource
    private ActiveMQHandler activeMQHandler;

    @Override
    public Integer marketOrder(Order order) {

        activeMQHandler.send("order", order);

//        Destination destination1 = new ActiveMQQueue("queue01");
//
//        Destination destination2 = new ActiveMQTopic("topic01");
//        jmsMessagingTemplate.convertAndSend(destination1, order);
//
//        jmsMessagingTemplate.convertAndSend(destination2, order);

        return 1;
    }

    @Override
    public Integer limitOrder(){
        return 1;
    }

    @Override
    public Integer stopOrder(){
        return 1;
    }

    @Override
    public Integer cancelOrder(){
        return 1;
    }

    @Override
    public List<BuyMarketDepth> getBuyMarketDepth(){
        return null;
    }
    @Override
    public List<OrderBlotter> getOrderBlotter(){
        return null;
    }
}
