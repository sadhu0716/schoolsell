package com.schoolsell.entity;

import java.io.Serializable;

public class Picture implements Serializable {
    private Integer pictureid;

    private Integer cid;

    private String picture;

    private static final long serialVersionUID = 1L;

    public Integer getPictureid() {
        return pictureid;
    }

    public void setPictureid(Integer pictureid) {
        this.pictureid = pictureid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Picture(Integer cid, String picture) {
        this.cid = cid;
        this.picture = picture;
    }

    public Picture(Integer pictureid, Integer cid, String picture) {
        this.pictureid = pictureid;
        this.cid = cid;
        this.picture = picture;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pictureid=").append(pictureid);
        sb.append(", cid=").append(cid);
        sb.append(", picture=").append(picture);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}