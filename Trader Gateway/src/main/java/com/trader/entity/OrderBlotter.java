package com.trader.entity;

import java.io.Serializable;
import java.util.Date;

public class OrderBlotter implements Serializable {
    private Integer tradeid;

    private String broker;

    private Integer productid;

    private Date period;

    private Integer price;

    private Integer quantity;

    private String iniTrader;

    private String iniSide;

    private String cplTrader;

    private String cplSide;

    public OrderBlotter(){}

    public OrderBlotter(Integer tradeid, String broker, Integer productid, Date period, Integer price, Integer quantity, String iniTrader, String iniSide, String cplTrader, String cplSide) {
        this.tradeid = tradeid;
        this.broker = broker;
        this.productid = productid;
        this.period = period;
        this.price = price;
        this.quantity = quantity;
        this.iniTrader = iniTrader;
        this.iniSide = iniSide;
        this.cplTrader = cplTrader;
        this.cplSide = cplSide;
    }

    private static final long serialVersionUID = 1L;

    public Integer getTradeid() {
        return tradeid;
    }

    public void setTradeid(Integer tradeid) {
        this.tradeid = tradeid;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getIniTrader() {
        return iniTrader;
    }

    public void setIniTrader(String iniTrader) {
        this.iniTrader = iniTrader;
    }

    public String getIniSide() {
        return iniSide;
    }

    public void setIniSide(String iniSide) {
        this.iniSide = iniSide;
    }

    public String getCplTrader() {
        return cplTrader;
    }

    public void setCplTrader(String cplTrader) {
        this.cplTrader = cplTrader;
    }

    public String getCplSide() {
        return cplSide;
    }

    public void setCplSide(String cplSide) {
        this.cplSide = cplSide;
    }

    @Override
    public String toString() {
        return "OrderBlotter{" +
                "tradeid=" + tradeid +
                ", broker='" + broker + '\'' +
                ", productid=" + productid +
                ", period=" + period +
                ", price=" + price +
                ", quantity=" + quantity +
                ", iniTrader='" + iniTrader + '\'' +
                ", iniSide='" + iniSide + '\'' +
                ", cplTrader='" + cplTrader + '\'' +
                ", cplSide='" + cplSide + '\'' +
                '}';
    }
}
