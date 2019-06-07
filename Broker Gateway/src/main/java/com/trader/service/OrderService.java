package com.trader.service;

import com.trader.entity.BuyMarketDepth;
import com.trader.entity.Order;
import com.trader.entity.OrderBlotter;

import javax.jms.JMSException;
import java.util.List;

public interface OrderService {
    Integer sendOrder(Order order) throws JMSException;

    List<BuyMarketDepth> getBuyMarketDepth();

    List<OrderBlotter> getOrderBlotter();
}
