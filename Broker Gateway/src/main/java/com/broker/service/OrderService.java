package com.broker.service;

import com.broker.entity.BuyMarketDepth;
import com.broker.entity.Order;
import com.broker.entity.Orderblotter;

import javax.jms.JMSException;
import java.util.LinkedList;
import java.util.List;

public interface OrderService {
    // 如果订单数量足够，则正常购买，返回0
    // 如果订单数量不足，则不购买，返回-1
    Integer buyMarketOrder(Order order) throws JMSException;

    Integer buyLimitOrder(Order order);

    Integer buyStopOrder(Order order);

    Integer buyCancelOrder(Order order);

    Integer sellMarketOrder(Order order) throws JMSException;

    Integer sellLimitOrder(Order order);

    Integer sellStopOrder(Order order);

    Integer sellCancelOrder(Order order);

    List<BuyMarketDepth> getMarketDepth();

    void sendOrderBlotter();

    void sendSellMarketDepth();

    void sendBuyMarketDepth();

}
