package com.trader.entity;

import java.util.LinkedList;
import java.util.List;

public class Orders {
    public List<Order> orders = new LinkedList<>();

    @Override
    public String toString() {
        return "Orders{" +
                "orders=" + orders +
                '}';
    }
}
