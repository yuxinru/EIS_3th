package com.trader.entity;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private Integer orderId;

    private String username;

    private String type;

    private String side;

    private Integer productId;

    private Integer quantity;

    private String broker;

    private Integer price;

    private String period;

    private String product;

    private Integer cancelId;

    private String strategy;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getCancelId() {
        return cancelId;
    }

    public void setCancelId(Integer cancelId) {
        this.cancelId = cancelId;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", username='" + username + '\'' +
                ", type='" + type + '\'' +
                ", side='" + side + '\'' +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", broker='" + broker + '\'' +
                ", price=" + price +
                ", period='" + period + '\'' +
                ", product='" + product + '\'' +
                ", cancelId=" + cancelId +
                ", strategy='" + strategy + '\'' +
                '}';
    }
}