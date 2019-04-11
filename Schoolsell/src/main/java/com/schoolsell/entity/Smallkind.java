package com.schoolsell.entity;

import java.io.Serializable;

public class Smallkind implements Serializable {
    private Integer smallkindid;

    private String smallkindname;

    private Integer bigkindid;

    private String smallkindpicture;

    private static final long serialVersionUID = 1L;

    public Integer getSmallkindid() {
        return smallkindid;
    }

    public void setSmallkindid(Integer smallkindid) {
        this.smallkindid = smallkindid;
    }

    public String getSmallkindname() {
        return smallkindname;
    }

    public void setSmallkindname(String smallkindname) {
        this.smallkindname = smallkindname == null ? null : smallkindname.trim();
    }

    public Integer getBigkindid() {
        return bigkindid;
    }

    public void setBigkindid(Integer bigkindid) {
        this.bigkindid = bigkindid;
    }

    public String getSmallkindpicture() {
        return smallkindpicture;
    }

    public void setSmallkindpicture(String smallkindpicture) {
        this.smallkindpicture = smallkindpicture == null ? null : smallkindpicture.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", smallkindid=").append(smallkindid);
        sb.append(", smallkindname=").append(smallkindname);
        sb.append(", bigkindid=").append(bigkindid);
        sb.append(", smallkindpicture=").append(smallkindpicture);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}