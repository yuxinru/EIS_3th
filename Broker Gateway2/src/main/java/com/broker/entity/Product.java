package com.broker.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * product
 * @author 
 */
public class Product implements Serializable {
    private Integer productid;

    private String name;

    private Date period;

    private String introduction;

    private static final long serialVersionUID = 1L;

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}