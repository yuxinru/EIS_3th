package com.broker.receiver;

import com.alibaba.fastjson.JSONObject;
import com.broker.service.OrderService;
import com.broker.parameter.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

@EnableJms
@Slf4j
@Component
public class MQReceiver {
    @Resource
    private OrderService orderService;

    private Semaphore semaphore1=new Semaphore(1, true);
    private Semaphore semaphore2=new Semaphore(1, true);
    private Semaphore semaphore3=new Semaphore(1, true);
    private Semaphore semaphore4=new Semaphore(1, true);

    private void dealOrder(Order order) throws JMSException {
        if(order.getSide().equals("buy")){
            if(order.getType().equals("market")){
                System.out.println("market");
                orderService.buyMarketOrder(order);
            }
            if(order.getType().equals("limit") ){
                orderService.buyLimitOrder(order);
            }
            if(order.getType().equals("cancel") ){
                orderService.buyCancelOrder(order);
            }
            if(order.getType().equals("stop")){
                orderService.buyStopOrder(order);
            }
        }
        if (order.getSide().equals("sell")){
            if(order.getType().equals("market")){
                System.out.println("market");
                orderService.sellMarketOrder(order);
            }
            if(order.getType().equals("limit") ){
                orderService.sellLimitOrder(order);
            }
            if(order.getType().equals("cancel") ){
                orderService.sellCancelOrder(order);
            }
            if(order.getType().equals("stop")){
                orderService.sellStopOrder(order);
            }
        }
    }
    @JmsListener(destination = "order1")
    public void listen1(String msg) throws JMSException, InterruptedException {
        Order order = JSONObject.parseObject(msg, Order.class);
        log.info(order.toString());
        semaphore1.acquire();
        dealOrder(order);
        semaphore1.release();
    }
    @JmsListener(destination = "order2")
    public void listen2(String msg) throws JMSException, InterruptedException {
        Order order = JSONObject.parseObject(msg, Order.class);
        log.info(order.toString());
        semaphore2.acquire();
        dealOrder(order);
        semaphore2.release();
    }
    @JmsListener(destination = "order3")
    public void listen3(String msg) throws JMSException, InterruptedException {
        Order order = JSONObject.parseObject(msg, Order.class);
        log.info(order.toString());
        semaphore3.acquire();
        dealOrder(order);
        semaphore3.release();
    }
    @JmsListener(destination = "order4")
    public void listen4(String msg) throws JMSException, InterruptedException{
        Order order = JSONObject.parseObject(msg, Order.class);
        log.info(order.toString());
        semaphore4.acquire();
        dealOrder(order);
        semaphore4.release();
    }

}
