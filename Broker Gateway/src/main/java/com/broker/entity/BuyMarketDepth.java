package com.broker.entity;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeMap;

public class BuyMarketDepth {
    public Integer total = 0;

    public TreeMap<Integer, LinkedList<Order>> map = new TreeMap<>();

}