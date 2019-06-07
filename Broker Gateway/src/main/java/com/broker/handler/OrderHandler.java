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
        redisHandler.set("buyMarketDepth" + productId, JSONObject.toJSONString(buyMarketDepth));

        buyMarketDepth = JSONObject.parseObject((String)redisHandler.get("buyMarketDepth" + productId), BuyMarketDepth.class);
        log.info("buyMarketDepth Map "+ buyMarketDepth.map.toString());
        log.info("buyMarketDepth amountMap "+ buyMarketDepth.amountMap.toString());
        log.info("buyMarketDepth 总量 "+ buyMarketDepth.total);
        //log.info("get 1250 "+ marketDepth.map.ceilingKey(1250));
        //log.info("get 111 "+ marketDepth.map.ceilingKey(111));
        return true;
    }
    public Boolean setSellMarketDepth(SellMarketDepth sellMarketDepth, Integer productId){
        log.info("sellMarketDepth存到redis");
        redisHandler.set("sellMarketDepth" + productId, JSONObject.toJSONString(sellMarketDepth));

        sellMarketDepth = JSONObject.parseObject((String)redisHandler.get("sellMarketDepth" + productId), SellMarketDepth.class);
        log.info("sellMarketDepth Map "+ sellMarketDepth.map.toString());
        log.info("sellMarketDepth amountMap "+ sellMarketDepth.amountMap.toString());
        log.info("sellMarketDepth 总量 "+ sellMarketDepth.total);
        //log.info("get 1250 "+ marketDepth.map.ceilingKey(1250));
        //log.info("get 111 "+ marketDepth.map.ceilingKey(111));
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
        redisHandler.set("sellStopOrder" + productId, JSONObject.toJSONString(sellStopOrder));

        sellStopOrder = JSONObject.parseObject((String) redisHandler.get("sellStopOrder" + productId), SellStopOrder.class);
        log.info("sellStopOrder结果 "+sellStopOrder.stopOrderMap.toString());
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
        redisHandler.set("buyStopOrder" + productId, JSONObject.toJSONString(buyStopOrder));

        buyStopOrder = JSONObject.parseObject((String) redisHandler.get("sellStopOrder" + productId), BuyStopOrder.class);
        log.info("buyStopOrder结果 "+buyStopOrder.stopOrderMap.toString());
        return true;
    }
    public Boolean setOrderBlotter(Orderblotter orderblotter, Integer productId){
        log.info("orderBlotter存到redis" + orderblotter.toString());
        //redisHandler.lSet("orderBlotter" + productId, orderblotter, 200);
        redisHandler.lSet("orderBlotter" + productId, orderblotter);
        return true;
    }
    public List<Object> getOrderBlotter(Integer productId){
        List<Object> orderBlotter;
        orderBlotter =  redisHandler.lGet("orderBlotter" + productId, 0, redisHandler.lGetListSize("orderBlotter"));
        List<Orderblotter> orderblotter;
//        if(tmp instanceof List<Orderblotter>{
//
//        }
//        orderblotter = (List<Orderblotter>)tmp;
        log.info(((Orderblotter)orderBlotter.get(0)).getBroker());

        return orderBlotter;
    }
}
