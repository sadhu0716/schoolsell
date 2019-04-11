package com.schoolsell.entity;

import java.io.Serializable;

public class Shopcar implements Serializable {
    private Integer shopcarid;

    private String userid;

    private Integer cid;

    private Integer amount;

    private Integer isfinish;

    private static final long serialVersionUID = 1L;

    public Shopcar(String userid, Integer cid, Integer amount, Integer isfinish) {
        this.userid = userid;
        this.cid = cid;
        this.amount = amount;
        this.isfinish = isfinish;
    }

    public Shopcar(Integer shopcarid, String userid, Integer cid, Integer amount, Integer isfinish) {
        this.shopcarid = shopcarid;
        this.userid = userid;
        this.cid = cid;
        this.amount = amount;
        this.isfinish = isfinish;
    }

    public Integer getShopcarid() {
        return shopcarid;
    }

    public void setShopcarid(Integer shopcarid) {
        this.shopcarid = shopcarid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
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

    public Integer getIsfinish() {
        return isfinish;
    }

    public void setIsfinish(Integer isfinish) {
        this.isfinish = isfinish;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", shopcarid=").append(shopcarid);
        sb.append(", userid=").append(userid);
        sb.append(", cid=").append(cid);
        sb.append(", amount=").append(amount);
        sb.append(", isfinish=").append(isfinish);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}