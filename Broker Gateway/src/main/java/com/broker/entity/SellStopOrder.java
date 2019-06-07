package com.broker.entity;

import com.broker.parameter.Order;

import java.util.LinkedList;
import java.util.TreeMap;

public class SellStopOrder {
    public Integer total = 0;

    public TreeMap<Integer, LinkedList<Order>> stopOrderMap;
}
