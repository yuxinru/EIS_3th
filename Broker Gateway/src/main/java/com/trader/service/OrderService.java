package com.trader.service;

import com.trader.entity.BuyMarketDepth;
import com.trader.entity.Order;
import com.trader.entity.OrderBlotter;

import javax.jms.JMSException;
import java.util.List;

public interface OrderService {
    Integer marketOrder(Order order) throws JMSException;

    Integer limitOrder();

    Integer stopOrder();

    Integer cancelOrder();

    List<BuyMarketDepth> getBuyMarketDepth();

    List<OrderBlotter> getOrderBlotter();
}
