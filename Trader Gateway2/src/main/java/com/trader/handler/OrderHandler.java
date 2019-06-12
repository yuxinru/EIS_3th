package com.trader.handler;

import com.alibaba.fastjson.JSONObject;
import com.trader.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.ListIterator;

@Component
@Slf4j
public class OrderHandler {
    @Resource
    RedisHandler redisHandler;


    public MarketDepths getMarketDepths(Integer productId){

        if(redisHandler.get("MarketDepths" + productId) == null){
            return null;
        }
        MarketDepths marketDepths;
        marketDepths = JSONObject.parseObject((String) redisHandler.get("MarketDepths" + productId), MarketDepths.class);
        log.info("redis中得到MarketDepths"+ productId);
        log.info(marketDepths.toString());
        return marketDepths;
    }

    public Boolean setMarketDepths(MarketDepths marketDepths, Integer productId){
        log.info("MarketDepths"+ productId +"存到redis");
        log.info(marketDepths.toString());
        redisHandler.set("MarketDepths" + productId, JSONObject.toJSONString(marketDepths));
        return true;
    }

    public Boolean setOrderBlotters2(OrderBlotters orderBlotters){
        log.info("OrderBlotter2存到redis" + orderBlotters.toString());

        redisHandler.set("OrderBlotters2", JSONObject.toJSONString(orderBlotters));
        return true;
    }
    public Boolean setOrderBlotters4(OrderBlotters orderBlotters){
        log.info("OrderBlotter14存到redis" + orderBlotters.toString());

        redisHandler.set("OrderBlotters4", JSONObject.toJSONString(orderBlotters));
        return true;
    }
    public List<OrderBlotter> getOrderBlotters(){

        OrderBlotters orderBlotters2 =  JSONObject.parseObject((String) redisHandler.get("OrderBlotters2"), OrderBlotters.class);
        OrderBlotters orderBlotters4 =  JSONObject.parseObject((String) redisHandler.get("OrderBlotters4"), OrderBlotters.class);
        log.info("redis中得到orderBlotters");
        if(orderBlotters2 == null){
            return orderBlotters4.orderBlotters;
        }
        if(orderBlotters4 == null){
            return orderBlotters2.orderBlotters;
        }
        orderBlotters2.orderBlotters.addAll(orderBlotters4.orderBlotters);
        log.info(orderBlotters2.toString());
        return orderBlotters2.orderBlotters;
    }
    public Boolean setMyOrders(Orders orders){
        log.info("Orders" + orders.toString());

        redisHandler.set("MyOrders", JSONObject.toJSONString(orders));
        return true;
    }
    public List<Order> getMyOrders(){

        Orders orders =  JSONObject.parseObject((String) redisHandler.get("MyOrders"), Orders.class);

        log.info("redis中得到Orders");
        log.info(orders.toString());
        return orders.orders;
    }
}

