package com.trader.receiver;

import com.alibaba.fastjson.JSONObject;

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

    @JmsListener(destination = "trader")
    public void listen(String msg) throws JMSException {
        log.info(msg);
        OrderBlotter orderBlotter = JSONObject.parseObject(msg, OrderBlotter.class);
        log.info(orderBlotter.toString());
    }
}
