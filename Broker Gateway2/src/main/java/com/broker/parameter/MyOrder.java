package com.broker.parameter;

import java.io.Serializable;

public class MyOrder implements Serializable {
    public int productId;

    public String username;

    public int trader;

    private static final long serialVersionUID = 1L;

    public MyOrder(String username, int productId, int trader) {
        this.username = username;
        this.productId = productId;
        this.trader = trader;
    }

    @Override
    public String toString() {
        return "MyOrder{" +
                "productId=" + productId +
                ", username='" + username + '\'' +
                ", trader=" + trader +
                '}';
    }
}
