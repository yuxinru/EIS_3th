package com.trader.handler;

import com.alibaba.fastjson.JSONObject;
import com.trader.entity.MarketDepths;
import com.trader.entity.OrderBlotter;
import com.trader.entity.OrderBlotters;
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
        log.info("redis中得到MarketDepths");
        log.info(marketDepths.toString());
        return marketDepths;
    }

    public Boolean setMarketDepths(MarketDepths marketDepths, Integer productId){
        log.info("MarketDepths存到redis");
        log.info(marketDepths.toString());
        redisHandler.set("MarketDepths" + productId, JSONObject.toJSONString(marketDepths));
        return true;
    }

    public Boolean setOrderBlotters(OrderBlotters orderBlotters){
        log.info("OrderBlotter存到redis" + orderBlotters.toString());

        redisHandler.set("OrderBlotters", orderBlotters);
        return true;
    }
    public OrderBlotters getOrderBlotters(){

        OrderBlotters orderBlotters =  JSONObject.parseObject((String) redisHandler.get("OrderBlotters"), OrderBlotters.class);

        log.info("redis中得到orderBlotters");
        log.info(orderBlotters.toString());
        return orderBlotters;
    }
}

