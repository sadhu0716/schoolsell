package com.schoolsell.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Order implements Serializable {
    private Integer orderid;

    private Timestamp orderdate;

    private Integer state;

    private Timestamp enddate;

    private Boolean isonline;

    private String buyerid;

    private String sellerid;

    private String buyername;

    private String sellername;

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public void setOrderdate(Timestamp orderdate) {
        this.orderdate = orderdate;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setEnddate(Timestamp enddate) {
        this.enddate = enddate;
    }

    public void setIsonline(Boolean isonline) {
        this.isonline = isonline;
    }

    public void setBuyerid(String buyerid) {
        this.buyerid = buyerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public void setBuyername(String buyername) {
        this.buyername = buyername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public Timestamp getOrderdate() {
        return orderdate;
    }

    public Integer getState() {
        return state;
    }

    public Timestamp getEnddate() {
        return enddate;
    }

    public Boolean getIsonline() {
        return isonline;
    }

    public String getBuyerid() {
        return buyerid;
    }

    public String getSellerid() {
        return sellerid;
    }

    public String getBuyername() {
        return buyername;
    }

    public String getSellername() {
        return sellername;
    }

    public Order(Timestamp orderdate, Integer state, Timestamp enddate, Boolean isonline, String buyerid, String sellerid, String buyername, String sellername) {
        this.orderdate = orderdate;
        this.state = state;
        this.enddate = enddate;
        this.isonline = isonline;
        this.buyerid = buyerid;
        this.sellerid = sellerid;
        this.buyername = buyername;
        this.sellername = sellername;
    }

    public Order(Timestamp orderdate, Integer state, Boolean isonline, String buyerid, String sellerid, String buyername, String sellername) {
        this.orderdate = orderdate;
        this.state = state;
        this.isonline = isonline;
        this.buyerid = buyerid;
        this.sellerid = sellerid;
        this.buyername = buyername;
        this.sellername = sellername;
    }

    public Order(Integer orderid, Timestamp orderdate, Integer state, Timestamp enddate, Boolean isonline, String buyerid, String sellerid, String buyername, String sellername) {
        this.orderid = orderid;
        this.orderdate = orderdate;
        this.state = state;
        this.enddate = enddate;
        this.isonline = isonline;
        this.buyerid = buyerid;
        this.sellerid = sellerid;
        this.buyername = buyername;
        this.sellername = sellername;
    }
}