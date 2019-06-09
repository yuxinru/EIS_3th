package com.trader.entity;


public class MarketDepth {
    public Integer buyLevel;

    public Integer buyVol;

    public Integer price;

    public Integer sellVol;

    public Integer sellLevel;

    public MarketDepth(Integer buyLevel, Integer buyVol, Integer price, Integer sellVol, Integer sellLevel) {
        this.buyLevel = buyLevel;
        this.buyVol = buyVol;
        this.price = price;
        this.sellVol = sellVol;
        this.sellLevel = sellLevel;
    }

    @Override
    public String toString() {
        return "MarketDepth{" +
                "buyLevel=" + buyLevel +
                ", buyVol=" + buyVol +
                ", price=" + price +
                ", sellVol=" + sellVol +
                ", sellLevel=" + sellLevel +
                '}';
    }
}