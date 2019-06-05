package com.broker.service;

import com.broker.entity.BuyMarketDepth;
import com.broker.parameter.Order;

import javax.jms.JMSException;
import java.util.List;

public interface OrderService {
    Integer buyMarketOrder(Order order) throws JMSException;

    Integer buyLimitOrder(Order order);

    Integer buyStopOrder(Order order);

    Integer buyCancelOrder(Order order);

    Integer sellMarketOrder(Order order) throws JMSException;

    Integer sellLimitOrder(Order order);

    Integer sellStopOrder(Order order);

    Integer sellCancelOrder(Order order);

    List<BuyMarketDepth> getBuyMarketDepth();

    void sendOrderBlotter();

    void sendSellMarketDepth();

    void sendBuyMarketDepth();

}
