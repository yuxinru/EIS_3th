package com.broker.service;

import com.broker.entity.MarketDepth;
import com.broker.entity.Order;
import com.broker.entity.Orderblotter;

import javax.jms.JMSException;
import java.util.List;

public interface OrderService {
    Integer buyMarketOrder(Order order) throws JMSException;

    Integer buyLimitOrder(Order order);

    Integer buyStopOrder(Order order);

    Integer sellMarketOrder(Order order) throws JMSException;

    Integer sellLimitOrder(Order order);

    Integer sellStopOrder(Order order);

    Integer cancelOrder(Order order);

    List<Orderblotter> getOrderBlotter();

    List<MarketDepth> getMarketDepth(int productId);

    void getMyOrder(String username, int productId, int trader);

    void sendOrderBlotters();

    void sendMarketDepths();


}
