package com.schoolsell.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Ordercommodity implements Serializable {
    private Integer ocid;

    private Integer orderid;

    private Integer cid;

    private Integer amount;

    private BigDecimal cprice;

    private String cname;

    private Boolean bargain;

    public Ordercommodity(Integer orderid, Integer cid, Integer amount, BigDecimal cprice, String cname, Boolean bargain) {
        this.orderid = orderid;
        this.cid = cid;
        this.amount = amount;
        this.cprice = cprice;
        this.cname = cname;
        this.bargain = bargain;
    }

    public Ordercommodity(Integer ocid, Integer orderid, Integer cid, Integer amount, BigDecimal cprice, String cname, Boolean bargain) {
        this.ocid = ocid;
        this.orderid = orderid;
        this.cid = cid;
        this.amount = amount;
        this.cprice = cprice;
        this.cname = cname;
        this.bargain = bargain;
    }

    private static final long serialVersionUID = 1L;

    public Integer getOcid() {
        return ocid;
    }

    public void setOcid(Integer ocid) {
        this.ocid = ocid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getCprice() {
        return cprice;
    }

    public void setCprice(BigDecimal cprice) {
        this.cprice = cprice;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public Boolean getBargain() {
        return bargain;
    }

    public void setBargain(Boolean bargain) {
        this.bargain = bargain;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ocid=").append(ocid);
        sb.append(", orderid=").append(orderid);
        sb.append(", cid=").append(cid);
        sb.append(", amount=").append(amount);
        sb.append(", cprice=").append(cprice);
        sb.append(", cname=").append(cname);
        sb.append(", bargain=").append(bargain);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}