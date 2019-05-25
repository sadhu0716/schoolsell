package com.schoolsell.entity;

import java.io.Serializable;

public class User implements Serializable {
    private String userID;

    private String filePath;

    private String gender;

    private String userName;

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getGender() {
        return gender;
    }

    public String getUserName() {
        return userName;
    }

    public User() {
    }

    public User(String userID, String filePath, String gender, String userName) {
        this.userID = userID;
        this.filePath = filePath;
        this.gender = gender;
        this.userName = userName;
    }
}