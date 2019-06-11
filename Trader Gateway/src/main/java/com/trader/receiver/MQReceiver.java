package com.trader.receiver;

import com.alibaba.fastjson.JSONObject;

import com.trader.entity.MarketDepths;
import com.trader.entity.OrderBlotters;
import com.trader.entity.Orders;
import com.trader.handler.OrderHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;

@EnableJms
@Component
@Slf4j
public class MQReceiver {

    @Resource
    OrderHandler orderHandler;

    @JmsListener(destination = "OrderBlotters1")
    public void listenBlotter1(String msg) throws JMSException {
        OrderBlotters orderBlotters = JSONObject.parseObject(msg, OrderBlotters.class);
        log.info("OrderBlotters1 接收到" + orderBlotters.toString());
        orderHandler.setOrderBlotters1(orderBlotters);
    }
    @JmsListener(destination = "OrderBlotters3")
    public void listenBlotter3(String msg) throws JMSException {
        OrderBlotters orderBlotters = JSONObject.parseObject(msg, OrderBlotters.class);
        log.info("OrderBlotters3 接收到" + orderBlotters.toString());
        orderHandler.setOrderBlotters3(orderBlotters);
    }

    @JmsListener(destination = "MarketDepths1")
    public void listenDepths1(String msg) throws JMSException {
        MarketDepths marketDepths = JSONObject.parseObject(msg, MarketDepths.class);
        //log.info(marketDepths.toString());
        orderHandler.setMarketDepths(marketDepths, 1);
    }
    @JmsListener(destination = "MarketDepths2")
    public void listenDepths2(String msg) throws JMSException {
        MarketDepths marketDepths = JSONObject.parseObject(msg, MarketDepths.class);
        log.info("MarketDepths2 接收到" + marketDepths.toString());
        orderHandler.setMarketDepths(marketDepths, 2);
    }
    @JmsListener(destination = "MarketDepths3")
    public void listenDepths3(String msg) throws JMSException {
        MarketDepths marketDepths = JSONObject.parseObject(msg, MarketDepths.class);
        //log.info(marketDepths.toString());
        orderHandler.setMarketDepths(marketDepths, 3);
    }
    @JmsListener(destination = "MarketDepths4")
    public void listenDepths4(String msg) throws JMSException {
        MarketDepths marketDepths = JSONObject.parseObject(msg, MarketDepths.class);
        //log.info(marketDepths.toString());
        orderHandler.setMarketDepths(marketDepths, 4);
    }
    @JmsListener(destination = "MarketDepths5")
    public void listenDepths5(String msg) throws JMSException {
        MarketDepths marketDepths = JSONObject.parseObject(msg, MarketDepths.class);
        //log.info(marketDepths.toString());
        orderHandler.setMarketDepths(marketDepths, 5);
    }
    @JmsListener(destination = "MarketDepths6")
    public void listenDepths6(String msg) throws JMSException {
        MarketDepths marketDepths = JSONObject.parseObject(msg, MarketDepths.class);
        log.info(marketDepths.toString());
        orderHandler.setMarketDepths(marketDepths, 6);
    }
    @JmsListener(destination = "MarketDepths7")
    public void listenDepths7(String msg) throws JMSException {
        MarketDepths marketDepths = JSONObject.parseObject(msg, MarketDepths.class);
       // log.info(marketDepths.toString());
        orderHandler.setMarketDepths(marketDepths, 7);
    }
    @JmsListener(destination = "MarketDepths8")
    public void listenDepths8(String msg) throws JMSException {
        MarketDepths marketDepths = JSONObject.parseObject(msg, MarketDepths.class);
        //log.info(marketDepths.toString());
        orderHandler.setMarketDepths(marketDepths, 8);
    }
    @JmsListener(destination = "MarketDepths9")
    public void listenDepths9(String msg) throws JMSException {
        MarketDepths marketDepths = JSONObject.parseObject(msg, MarketDepths.class);
       // log.info(marketDepths.toString());
        orderHandler.setMarketDepths(marketDepths, 9);
    }

    @JmsListener(destination = "MarketDepths10")
    public void listenDepths10(String msg) throws JMSException {
        MarketDepths marketDepths = JSONObject.parseObject(msg, MarketDepths.class);
        //log.info(marketDepths.toString());
        orderHandler.setMarketDepths(marketDepths, 10);
    }
    @JmsListener(destination = "MyOrder1")
    public void listenMyOrder(String msg) throws JMSException {
        Orders orders = JSONObject.parseObject(msg, Orders.class);
        //log.info(marketDepths.toString());
        orderHandler.setMyOrders(orders);
    }

}
