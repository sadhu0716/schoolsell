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

    private String address;

    private String buyerphone;

    private String sellerphone;

    private String buyername;

    private String sellername;

    public Order(Integer orderid, Timestamp orderdate, Integer state, Timestamp enddate, Boolean isonline, String buyerid, String sellerid, String address, String buyerphone, String sellerphone, String buyername, String sellername) {
        this.orderid = orderid;
        this.orderdate = orderdate;
        this.state = state;
        this.enddate = enddate;
        this.isonline = isonline;
        this.buyerid = buyerid;
        this.sellerid = sellerid;
        this.address = address;
        this.buyerphone = buyerphone;
        this.sellerphone = sellerphone;
        this.buyername = buyername;
        this.sellername = sellername;
    }

    public Order(Timestamp orderdate, Integer state, Boolean isonline, String buyerid, String sellerid, String address, String buyerphone, String sellerphone, String buyername, String sellername) {
        this.orderdate = orderdate;
        this.state = state;
        this.isonline = isonline;
        this.buyerid = buyerid;
        this.sellerid = sellerid;
        this.address = address;
        this.buyerphone = buyerphone;
        this.sellerphone = sellerphone;
        this.buyername = buyername;
        this.sellername = sellername;
    }

    private static final long serialVersionUID = 1L;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Timestamp getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Timestamp orderdate) {
        this.orderdate = orderdate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Timestamp getEnddate() {
        return enddate;
    }

    public void setEnddate(Timestamp enddate) {
        this.enddate = enddate;
    }

    public Boolean getIsonline() {
        return isonline;
    }

    public void setIsonline(Boolean isonline) {
        this.isonline = isonline;
    }

    public String getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(String buyerid) {
        this.buyerid = buyerid == null ? null : buyerid.trim();
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid == null ? null : sellerid.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getBuyerphone() {
        return buyerphone;
    }

    public void setBuyerphone(String buyerphone) {
        this.buyerphone = buyerphone == null ? null : buyerphone.trim();
    }

    public String getSellerphone() {
        return sellerphone;
    }

    public void setSellerphone(String sellerphone) {
        this.sellerphone = sellerphone == null ? null : sellerphone.trim();
    }

    public String getBuyername() {
        return buyername;
    }

    public void setBuyername(String buyername) {
        this.buyername = buyername == null ? null : buyername.trim();
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername == null ? null : sellername.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderid=").append(orderid);
        sb.append(", orderdate=").append(orderdate);
        sb.append(", state=").append(state);
        sb.append(", enddate=").append(enddate);
        sb.append(", isonline=").append(isonline);
        sb.append(", buyerid=").append(buyerid);
        sb.append(", sellerid=").append(sellerid);
        sb.append(", address=").append(address);
        sb.append(", buyerphone=").append(buyerphone);
        sb.append(", sellerphone=").append(sellerphone);
        sb.append(", buyername=").append(buyername);
        sb.append(", sellername=").append(sellername);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}