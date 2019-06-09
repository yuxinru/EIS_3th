package com.broker.handler;

import com.alibaba.fastjson.JSONObject;
import com.broker.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.TreeMap;

@Component
@Slf4j
public class OrderHandler {
    @Resource
    RedisHandler redisHandler;

    private int getBlotterId(){
        if(redisHandler.get("blotterId") == null){
            redisHandler.set("blotterId" , 2);
            return 1;
        }
        int ret;
        ret = (Integer) redisHandler.get("blotterId" );
        redisHandler.set("blotterId" , ret + 1);
        return ret;
    }
    public int getBuyOrderId(Integer productId){
        if(redisHandler.get("buyOrderId" + productId) == null){
            redisHandler.set("buyOrderId" + productId, 2);
            return 1;
        }
        int ret;
        ret = (Integer) redisHandler.get("buyOrderId" + productId);
        redisHandler.set("buyOrderId" + productId, ret + 1);
        return ret;
    }
    public int getSellOrderId(Integer productId){
        if(redisHandler.get("sellOrderId" + productId) == null){
            redisHandler.set("sellOrderId" + productId, 2);
            return 1;
        }
        int ret;
        ret = (Integer)redisHandler.get("sellOrderId" + productId);
        redisHandler.set("sellOrderId" + productId, ret + 1);
        return ret;
    }
    public BuyMarketDepth getBuyMarketDepth(Integer productId){

        if(redisHandler.get("buyMarketDepth" + productId) == null){
            BuyMarketDepth marketDepth = new BuyMarketDepth();

            redisHandler.set("buyMarketDepth" + productId, JSONObject.toJSONString(marketDepth));
            return marketDepth;

        }
        BuyMarketDepth buyMarketDepth;
        buyMarketDepth = JSONObject.parseObject((String) redisHandler.get("buyMarketDepth" + productId), BuyMarketDepth.class);
        log.info("redis中得到buyMarketDepth" + productId);
        log.info("buyMarketDepth Map "+ buyMarketDepth.map.toString());
        log.info("buyMarketDepth 总量 "+ buyMarketDepth.total);
        return buyMarketDepth;
    }
    public SellMarketDepth getSellMarketDepth(Integer productId){

        if(redisHandler.get("sellMarketDepth" + productId) == null){
            SellMarketDepth marketDepth = new SellMarketDepth();
            redisHandler.set("sellMarketDepth"  + productId, JSONObject.toJSONString(marketDepth));
            return marketDepth;
        }
        SellMarketDepth sellMarketDepth;
        sellMarketDepth = JSONObject.parseObject((String) redisHandler.get("sellMarketDepth" + productId), SellMarketDepth.class);
        log.info("redis中得到sellMarketDepth" + productId);
        log.info("sellMarketDepth Map "+ sellMarketDepth.map.toString());
        log.info("sellMarketDepth 总量 "+ sellMarketDepth.total);
        return sellMarketDepth;
    }
    public Boolean setBuyMarketDepth(BuyMarketDepth buyMarketDepth, Integer productId){
        log.info("buyMarketDepth存到redis");
        log.info("buyMarketDepth Map "+ buyMarketDepth.map.toString());
        log.info("buyMarketDepth amountMap "+ buyMarketDepth.amountMap.toString());
        log.info("buyMarketDepth 总量 "+ buyMarketDepth.total);
        redisHandler.set("buyMarketDepth" + productId, JSONObject.toJSONString(buyMarketDepth));

        return true;
    }
    public Boolean setSellMarketDepth(SellMarketDepth sellMarketDepth, Integer productId){
        log.info("sellMarketDepth存到redis");
        log.info("sellMarketDepth Map "+ sellMarketDepth.map.toString());
        log.info("sellMarketDepth amountMap "+ sellMarketDepth.amountMap.toString());
        log.info("sellMarketDepth 总量 "+ sellMarketDepth.total);
        redisHandler.set("sellMarketDepth" + productId, JSONObject.toJSONString(sellMarketDepth));

        return true;
    }
    public SellStopOrder getSellStopOrder(Integer productId){
        if(redisHandler.get("sellStopOrder" + productId) == null){
            SellStopOrder sellStopOrder= new SellStopOrder();
            sellStopOrder.stopOrderMap = new TreeMap<>();
            redisHandler.set("sellStopOrder" + productId, JSONObject.toJSONString(sellStopOrder));
            return sellStopOrder;
        }
        SellStopOrder sellStopOrder;
        sellStopOrder = JSONObject.parseObject((String) redisHandler.get("sellStopOrder" + productId), SellStopOrder.class);

        return sellStopOrder;
    }
    public Boolean setSellStopOrder(SellStopOrder sellStopOrder, Integer productId){
        log.info("sellStopOrder存到redis");
        log.info("sellStopOrder结果 "+sellStopOrder.stopOrderMap.toString());
        redisHandler.set("sellStopOrder" + productId, JSONObject.toJSONString(sellStopOrder));

        return true;
    }
    public BuyStopOrder getBuyStopOrder(Integer productId){
        if(redisHandler.get("buyStopOrder" + productId) == null){
            BuyStopOrder buyStopOrder = new BuyStopOrder();
            buyStopOrder.stopOrderMap = new TreeMap<>();
            redisHandler.set("buyStopOrder" + productId, JSONObject.toJSONString(buyStopOrder));
            return buyStopOrder;
        }
        BuyStopOrder buyStopOrder;
        buyStopOrder = JSONObject.parseObject((String) redisHandler.get("buyStopOrder" + productId), BuyStopOrder.class);

        return buyStopOrder;
    }
    public Boolean setBuyStopOrder(BuyStopOrder buyStopOrder, Integer productId){
        log.info("buyStopOrder存到redis");
        log.info("buyStopOrder结果 "+buyStopOrder.stopOrderMap.toString());
        redisHandler.set("buyStopOrder" + productId, JSONObject.toJSONString(buyStopOrder));

        return true;
    }
    public Boolean setOrderBlotter(Orderblotter orderblotter){
        log.info("orderBlotter存到redis" + orderblotter.toString());
        //redisHandler.lSet("orderBlotter", orderblotter, 200);
        orderblotter.setTradeid(getBlotterId());
        redisHandler.lSet("orderBlotter", orderblotter);
        return true;
    }
    public OrderBlotters getOrderBlotters(){
        List<Object> objects;
        objects =  redisHandler.lGet("orderBlotter", 0, redisHandler.lGetListSize("orderBlotter"));
        OrderBlotters orderBlotters = new OrderBlotters();
        ListIterator<Object> listIterator = objects.listIterator();

        Object o;
        while(listIterator.hasNext()){
            o = listIterator.next();
            orderBlotters.orderblotters.add((Orderblotter)o);
        }
        log.info(orderBlotters.toString());
        return orderBlotters;
    }
}
