package com.broker.entity;

import java.util.Map;
import java.util.TreeMap;

public class MarketDepth {
    public TreeMap<Integer, Integer> buyMarketDepth = new TreeMap<>();

    public TreeMap<Integer, Integer> sellMarketDepth = new TreeMap<>();

    @Override
    public String toString() {
        return "MarketDepth{" +
                "buyMarketDepth=" + buyMarketDepth +
                ", sellMarketDepth=" + sellMarketDepth +
                '}';
    }

}
