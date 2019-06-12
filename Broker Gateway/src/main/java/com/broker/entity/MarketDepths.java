package com.broker.entity;

import java.util.LinkedList;
import java.util.List;

public class MarketDepths {
    public List<MarketDepth> marketDepths = new LinkedList<>();

    @Override
    public String toString() {
        return "MarketDepths{" +
                "marketDepths=" + marketDepths +
                '}';
    }
}
