package com.schoolsell.entity;

import java.io.Serializable;
import java.util.Date;

public class Jubao implements Serializable {
    private Integer jubaoid;

    private Date jubaodate;

    private String jubaouserid;

    private String description;

    private Integer ocid;

    private static final long serialVersionUID = 1L;

    public Integer getJubaoid() {
        return jubaoid;
    }

    public void setJubaoid(Integer jubaoid) {
        this.jubaoid = jubaoid;
    }

    public Date getJubaodate() {
        return jubaodate;
    }

    public void setJubaodate(Date jubaodate) {
        this.jubaodate = jubaodate;
    }

    public String getJubaouserid() {
        return jubaouserid;
    }

    public void setJubaouserid(String jubaouserid) {
        this.jubaouserid = jubaouserid == null ? null : jubaouserid.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getOcid() {
        return ocid;
    }

    public void setOcid(Integer ocid) {
        this.ocid = ocid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", jubaoid=").append(jubaoid);
        sb.append(", jubaodate=").append(jubaodate);
        sb.append(", jubaouserid=").append(jubaouserid);
        sb.append(", description=").append(description);
        sb.append(", ocid=").append(ocid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}