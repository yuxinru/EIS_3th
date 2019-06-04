package com.broker.receiver;

import com.alibaba.fastjson.JSONObject;
import com.broker.service.OrderService;
import com.broker.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.JMSException;
import java.util.Map;

@EnableJms
@Slf4j
@Component
public class MQReceiver {
    @Resource
    private OrderService orderService;

    @JmsListener(destination = "order")
    public void listen(String msg) throws JMSException {
        log.info(msg);
        Order order = JSONObject.parseObject(msg, Order.class);
        log.info(order.toString());
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

}
