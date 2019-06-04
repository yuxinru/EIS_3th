package com.broker.handler;

import com.alibaba.fastjson.JSONObject;
import com.broker.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.TreeMap;

@Component
@Slf4j
public class OrderHandler {
    @Resource
    RedisHandler redisHandler;

    public int getBuyOrderId(){
        if(redisHandler.get("buyOrderId") == null){
            redisHandler.set("buyOrderId", 2);
            return 1;
        }
        int ret;
        ret = (Integer) redisHandler.get("buyOrderId");
        redisHandler.set("buyOrderId", ret + 1);
        return ret;
    }
    public int getSellOrderId(){
        if(redisHandler.get("sellOrderId") == null){
            redisHandler.set("sellOrderId", 2);
            return 1;
        }
        int ret;
        ret = (Integer)redisHandler.get("sellOrderId");
        redisHandler.set("sellOrderId", ret + 1);
        return ret;
    }
    public BuyMarketDepth getBuyMarketDepth(){

        if(redisHandler.get("buyMarketDepth") == null){
            BuyMarketDepth marketDepth = new BuyMarketDepth();

            redisHandler.set("sellMarketDepth" , JSONObject.toJSONString(marketDepth));
            return marketDepth;

        }
        BuyMarketDepth buyMarketDepth;
        buyMarketDepth = JSONObject.parseObject((String) redisHandler.get("buyMarketDepth"), BuyMarketDepth.class);
        log.info("redis中得到buyMarketDepth");
        log.info("buyMarketDepth Map "+ buyMarketDepth.map.toString());
        log.info("buyMarketDepth 总量 "+ buyMarketDepth.total);
        return buyMarketDepth;
    }
    public SellMarketDepth getSellMarketDepth(){

        if(redisHandler.get("sellMarketDepth") == null){
            SellMarketDepth marketDepth = new SellMarketDepth();
            redisHandler.set("sellMarketDepth" , JSONObject.toJSONString(marketDepth));
            return marketDepth;
        }
        SellMarketDepth sellMarketDepth;
        sellMarketDepth = JSONObject.parseObject((String) redisHandler.get("sellMarketDepth"), SellMarketDepth.class);
        log.info("redis中得到sellMarketDepth");
        log.info("sellMarketDepth Map "+ sellMarketDepth.map.toString());
        log.info("sellMarketDepth 总量 "+ sellMarketDepth.total);
        return sellMarketDepth;
    }
    public Boolean setBuyMarketDepth(BuyMarketDepth buyMarketDepth){
        log.info("buyMarketDepth存到redis");
        redisHandler.set("buyMarketDepth", JSONObject.toJSONString(buyMarketDepth));

        buyMarketDepth = JSONObject.parseObject((String)redisHandler.get("buyMarketDepth"), BuyMarketDepth.class);
        log.info("buyMarketDepth Map "+ buyMarketDepth.map.toString());
        log.info("buyMarketDepth 总量 "+ buyMarketDepth.total);
        //log.info("get 1250 "+ marketDepth.map.ceilingKey(1250));
        //log.info("get 111 "+ marketDepth.map.ceilingKey(111));
        return true;
    }
    public Boolean setSellMarketDepth(SellMarketDepth sellMarketDepth){
        log.info("sellMarketDepth存到redis");
        redisHandler.set("sellMarketDepth", JSONObject.toJSONString(sellMarketDepth));

        sellMarketDepth = JSONObject.parseObject((String)redisHandler.get("sellMarketDepth"), SellMarketDepth.class);
        log.info("sellMarketDepth Map "+ sellMarketDepth.map.toString());
        log.info("sellMarketDepth 总量 "+ sellMarketDepth.total);
        //log.info("get 1250 "+ marketDepth.map.ceilingKey(1250));
        //log.info("get 111 "+ marketDepth.map.ceilingKey(111));
        return true;
    }
    public SellStopOrder getSellStopOrder(){
        if(redisHandler.get("sellStopOrder") == null){
            SellStopOrder sellStopOrder= new SellStopOrder();
            sellStopOrder.stopOrderMap = new TreeMap<>();
            redisHandler.set("sellStopOrder" , JSONObject.toJSONString(sellStopOrder));
            return sellStopOrder;
        }
        SellStopOrder sellStopOrder;
        sellStopOrder = JSONObject.parseObject((String) redisHandler.get("sellStopOrder"), SellStopOrder.class);

        return sellStopOrder;
    }
    public Boolean setSellStopOrder(SellStopOrder sellStopOrder){
        log.info("sellStopOrder存到redis");
        redisHandler.set("sellStopOrder", JSONObject.toJSONString(sellStopOrder));

        sellStopOrder = JSONObject.parseObject((String) redisHandler.get("sellStopOrder"), SellStopOrder.class);
        log.info("sellStopOrder结果 "+sellStopOrder.stopOrderMap.toString());
        return true;
    }
    public BuyStopOrder getBuyStopOrder(){
        if(redisHandler.get("buyStopOrder") == null){
            BuyStopOrder buyStopOrder = new BuyStopOrder();
            buyStopOrder.stopOrderMap = new TreeMap<>();
            redisHandler.set("buyStopOrder" , JSONObject.toJSONString(buyStopOrder));
            return buyStopOrder;
        }
        BuyStopOrder buyStopOrder;
        buyStopOrder = JSONObject.parseObject((String) redisHandler.get("buyStopOrder"), BuyStopOrder.class);

        return buyStopOrder;
    }
    public Boolean setBuyStopOrder(BuyStopOrder buyStopOrder){
        log.info("buyStopOrder存到redis");
        redisHandler.set("buyStopOrder", JSONObject.toJSONString(buyStopOrder));

        buyStopOrder = JSONObject.parseObject((String) redisHandler.get("sellStopOrder"), BuyStopOrder.class);
        log.info("buyStopOrder结果 "+buyStopOrder.stopOrderMap.toString());
        return true;
    }
    public Boolean setOrderBlotter(Orderblotter orderblotter){
        log.info("orderBlotter存到redis");
        redisHandler.lSet("orderBlotter", orderblotter, 200);
        return true;
    }
    public List<Object> getOrderBlotter(){
        List<Object> orderBlotter;
        orderBlotter =  redisHandler.lGet("orderBlotter", 0, redisHandler.lGetListSize("orderBlotter"));
        List<Orderblotter> orderblotter;
//        if(tmp instanceof List<Orderblotter>{
//
//        }
//        orderblotter = (List<Orderblotter>)tmp;
        log.info(((Orderblotter)orderBlotter.get(0)).getBroker());

        return orderBlotter;
    }
}
