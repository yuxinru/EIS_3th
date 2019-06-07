package com.trader.receiver;

import com.alibaba.fastjson.JSONObject;

import com.trader.entity.MarketDepth;
import com.trader.entity.OrderBlotter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@EnableJms
@Component
@Slf4j
public class MQReceiver {

    @JmsListener(destination = "OrderBlotter1")
    public void listenBlotter1(String msg) throws JMSException {
        OrderBlotter orderBlotter = JSONObject.parseObject(msg, OrderBlotter.class);
        log.info(orderBlotter.toString());
    }
    @JmsListener(destination = "OrderBlotter2")
    public void listenBlotter2(String msg) throws JMSException {
        OrderBlotter orderBlotter = JSONObject.parseObject(msg, OrderBlotter.class);
        log.info(orderBlotter.toString());
    }
    @JmsListener(destination = "OrderBlotter3")
    public void listenBlotter3(String msg) throws JMSException {
        OrderBlotter orderBlotter = JSONObject.parseObject(msg, OrderBlotter.class);
        log.info(orderBlotter.toString());
    }
    @JmsListener(destination = "OrderBlotter4")
    public void listenBlotter4(String msg) throws JMSException {
        OrderBlotter orderBlotter = JSONObject.parseObject(msg, OrderBlotter.class);
        log.info(orderBlotter.toString());
    }
    @JmsListener(destination = "MarketDepth1")
    public void listenDepth1(String msg) throws JMSException {
        MarketDepth marketDepth = JSONObject.parseObject(msg, MarketDepth.class);
        log.info(marketDepth.toString());
    }
    @JmsListener(destination = "MarketDepth2")
    public void listenDepth2(String msg) throws JMSException {
        MarketDepth marketDepth = JSONObject.parseObject(msg, MarketDepth.class);
        log.info(marketDepth.toString());
    }
    @JmsListener(destination = "MarketDepth3")
    public void listenDepth3(String msg) throws JMSException {
        MarketDepth marketDepth = JSONObject.parseObject(msg, MarketDepth.class);
        log.info(marketDepth.toString());
    }
    @JmsListener(destination = "MarketDepth4")
    public void listenDepth4(String msg) throws JMSException {
        MarketDepth marketDepth = JSONObject.parseObject(msg, MarketDepth.class);
        log.info(marketDepth.toString());
    }

}
