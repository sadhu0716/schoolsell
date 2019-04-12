package com.schoolsell.dto;

import java.math.BigDecimal;

public class OrderCommodityDto {

    private int amount;
    private int cID;
    private BigDecimal cPrice;
    private String cName;
    private boolean bargain;

    public OrderCommodityDto(int amount, int cID, BigDecimal cPrice, String cName, boolean bargain) {
        this.amount = amount;
        this.cID = cID;
        this.cPrice = cPrice;
        this.cName = cName;
        this.bargain = bargain;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    public void setcPrice(BigDecimal cPrice) {
        this.cPrice = cPrice;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public void setBargain(boolean bargain) {
        this.bargain = bargain;
    }

    public int getAmount() {
        return amount;
    }

    public int getcID() {
        return cID;
    }

    public BigDecimal getcPrice() {
        return cPrice;
    }

    public String getcName() {
        return cName;
    }

    public boolean isBargain() {
        return bargain;
    }
}
