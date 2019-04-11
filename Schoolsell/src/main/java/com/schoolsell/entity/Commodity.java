package com.schoolsell.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
@Component
public class Commodity implements Serializable {
    private Integer cid;

    private BigDecimal cprice;

    private Boolean bargain;

    private String usetime;

    private String cdescription;

    private Integer ischecked;

    private String sellerid;

    private String cname;

    private String kname;

    private String thumbnail;

    private Integer ccount;


    private static final long serialVersionUID = 1L;

    public Commodity() {
    }

    public Commodity(BigDecimal cprice, Boolean bargain, String usetime, String cdescription, Integer ischecked, String sellerid, String cname, String kname, String thumbnail, Integer ccount) {
        this.cprice = cprice;
        this.bargain = bargain;
        this.usetime = usetime;
        this.cdescription = cdescription;
        this.ischecked = ischecked;
        this.sellerid = sellerid;
        this.cname = cname;
        this.kname = kname;
        this.thumbnail = thumbnail;
        this.ccount = ccount;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public BigDecimal getCprice() {
        return cprice;
    }

    public void setCprice(BigDecimal cprice) {
        this.cprice = cprice;
    }

    public Boolean getBargain() {
        return bargain;
    }

    public void setBargain(Boolean bargain) {
        this.bargain = bargain;
    }

    public String getUsetime() {
        return usetime;
    }

    public void setUsetime(String usetime) {
        this.usetime = usetime == null ? null : usetime.trim();
    }

    public String getCdescription() {
        return cdescription;
    }

    public void setCdescription(String cdescription) {
        this.cdescription = cdescription == null ? null : cdescription.trim();
    }

    public Integer getIschecked() {
        return ischecked;
    }

    public void setIschecked(Integer ischecked) {
        this.ischecked = ischecked;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid == null ? null : sellerid.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    public String getKname() {
        return kname;
    }

    public void setKname(String kname) {
        this.kname = kname == null ? null : kname.trim();
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail == null ? null : thumbnail.trim();
    }

    public Integer getCcount() {
        return ccount;
    }

    public void setCcount(Integer ccount) {
        this.ccount = ccount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cid=").append(cid);
        sb.append(", cprice=").append(cprice);
        sb.append(", bargain=").append(bargain);
        sb.append(", usetime=").append(usetime);
        sb.append(", cdescription=").append(cdescription);
        sb.append(", ischecked=").append(ischecked);
        sb.append(", sellerid=").append(sellerid);
        sb.append(", cname=").append(cname);
        sb.append(", kname=").append(kname);
        sb.append(", thumbnail=").append(thumbnail);
        sb.append(", ccount=").append(ccount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}