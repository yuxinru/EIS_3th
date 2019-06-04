package com.broker.entity;

import java.util.LinkedList;
import java.util.TreeMap;

public class BuyStopOrder {
    public TreeMap<Integer, LinkedList<Order>> stopOrderMap;
}
