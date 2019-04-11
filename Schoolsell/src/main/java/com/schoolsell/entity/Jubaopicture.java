package com.schoolsell.entity;

import java.io.Serializable;

public class Jubaopicture implements Serializable {
    private Integer jubaopictureid;

    private Integer jubaoid;

    private String photo;

    private static final long serialVersionUID = 1L;

    public Integer getJubaopictureid() {
        return jubaopictureid;
    }

    public void setJubaopictureid(Integer jubaopictureid) {
        this.jubaopictureid = jubaopictureid;
    }

    public Integer getJubaoid() {
        return jubaoid;
    }

    public void setJubaoid(Integer jubaoid) {
        this.jubaoid = jubaoid;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", jubaopictureid=").append(jubaopictureid);
        sb.append(", jubaoid=").append(jubaoid);
        sb.append(", photo=").append(photo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}