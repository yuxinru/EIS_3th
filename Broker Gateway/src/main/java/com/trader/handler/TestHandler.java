package com.trader.handler;

import com.trader.entity.Order;
import com.trader.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;

@Component
@Slf4j
@EnableScheduling
@EnableAsync
public class TestHandler {

    @Resource
    private OrderService orderService;

    @Scheduled(fixedRate=100)
    @Async
    public void sendBuyLimitOrder()throws JMSException {
        double d = Math.random();
        int price = 1250 - (int)(d*10);
        int amount = 51 + (int)(d*100);
        Order order = new Order();
        order.setType("limit");
        order.setProductId(1);
        order.setQuantity(amount);
        order.setBroker("broker");
        order.setPrice(price);
        order.setSide("buy");
        log.info(order.toString());
        orderService.sendOrder(order);

    }
    @Scheduled(fixedRate=100)
    @Async
    public void sendSellLimitOrder() throws JMSException {
        double d = Math.random();
        int price = 1250 + (int)(d*10);
        int amount = 50 + (int)(d*100);
        Order order = new Order();
        order.setType("limit");
        order.setProductId(1);
        order.setQuantity(amount);
        order.setBroker("broker");
        order.setPrice(price);
        order.setSide("sell");
        orderService.sendOrder(order);
    }
    @Scheduled(fixedRate=100)
    @Async
    public void sendBuyMarketOrder()throws JMSException{
        double d = Math.random();
        int amount = 50+ (int)(d*100);
        Order order = new Order();
        order.setType("market");
        order.setProductId(1);
        order.setQuantity(amount);
        order.setBroker("broker");
        order.setSide("buy");
        orderService.sendOrder(order);
    }
    @Scheduled(fixedRate=100)
    @Async
    public void sendSellMarketOrder()throws JMSException{
        double d = Math.random();
        int amount = 49+(int)(d*100);
        Order order = new Order();
        order.setType("market");
        order.setProductId(1);
        order.setQuantity(amount);
        order.setBroker("broker");
        order.setSide("sell");
        orderService.sendOrder(order);
    }
}
