package com.trader.serviceImpl;

import com.trader.entity.MarketDepth;
import com.trader.entity.OrderBlotter;
import com.trader.entity.Order;
import com.trader.handler.ActiveMQHandler;
import com.trader.handler.OrderHandler;
import com.trader.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    @Resource
    private ActiveMQHandler activeMQHandler;

    @Resource
    OrderHandler orderHandler;
    @Override
    public Integer sendOrder(Order order) {
        if(order.getProductId()<1 || order.getProductId()>4 )
            return -1;
        activeMQHandler.send("order"+ order.getProductId(), order);

        return 1;
    }



    @Override
    public List<MarketDepth> getMarketDepths(int productId){
        return orderHandler.getMarketDepths(productId).marketDepths;
    }
    @Override
    public List<OrderBlotter> getOrderBlotters(){
        return orderHandler.getOrderBlotters().orderBlotters;
    }
}
