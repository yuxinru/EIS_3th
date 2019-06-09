package com.trader.service;

import com.trader.entity.MarketDepth;
import com.trader.entity.Order;
import com.trader.entity.OrderBlotter;

import javax.jms.JMSException;
import java.util.List;

public interface OrderService {
    Integer sendOrder(Order order) throws JMSException;

    List<MarketDepth> getMarketDepths(int productId);

    List<OrderBlotter> getOrderBlotters();
}
