package com.broker.entity;

import java.util.LinkedList;
import java.util.TreeMap;

public class SellMarketDepth {
    public Integer total = 0;

    public TreeMap<Integer, LinkedList<Order>> map = new TreeMap<>();
}
