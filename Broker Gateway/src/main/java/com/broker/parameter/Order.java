package com.broker.parameter;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private Integer orderId;

    //"market" "limit" "cancel" "stop"
    private String type;

    //"buy" "sell"
    private String side;

    private Integer productId;

    private Integer quantity;

    private String broker;

    private Integer price;

    private Date period;

    private Integer cancelId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public Integer getCancelId() {
        return cancelId;
    }

    public void setCancelId(Integer cancelId) {
        this.cancelId = cancelId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", type='" + type + '\'' +
                ", side='" + side + '\'' +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", broker='" + broker + '\'' +
                ", price=" + price +
                ", period=" + period +
                ", cancelId=" + cancelId +
                '}';
    }
}
