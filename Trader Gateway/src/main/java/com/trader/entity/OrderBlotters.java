package com.trader.entity;

import java.util.LinkedList;
import java.util.List;

public class OrderBlotters {
    public List<OrderBlotter> orderBlotters = new LinkedList<>();

    @Override
    public String toString() {
        return "OrderBlotters{" +
                "orderBlotters=" + orderBlotters +
                '}';
    }
}