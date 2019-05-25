package com.schoolsell.entity;

public class Wxuser {

    private Integer wxID;
    private String userID;
    private String openID;
    private String sessionKey;

    public Wxuser() {
    }

    public Wxuser(String userID, String openID, String sessionKey) {
        this.userID = userID;
        this.openID = openID;
        this.sessionKey = sessionKey;
    }

    public void setWxID(Integer wxID) {
        this.wxID = wxID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Integer getWxID() {
        return wxID;
    }

    public String getUserID() {
        return userID;
    }

    public String getOpenID() {
        return openID;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public Wxuser(Integer wxID, String userID, String openID, String sessionKey) {
        this.wxID = wxID;
        this.userID = userID;
        this.openID = openID;
        this.sessionKey = sessionKey;
    }
}
