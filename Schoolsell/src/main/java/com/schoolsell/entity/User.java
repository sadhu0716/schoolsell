package com.schoolsell.entity;

import java.io.Serializable;

public class User implements Serializable {
    private String userid;

    private String password;

    private String username;

    private String realname;

    private String idnumber;

    private String phonenumber;

    private String address;

    private Integer credibility;

    private String profilephoto;

    private Integer ischecked;

    private static final long serialVersionUID = 1L;

    public User() {
    }

    public User(String userid, String password, String username, String realname, String idnumber, String phonenumber, String address, Integer credibility, String profilephoto, Integer ischecked) {
        this.userid = userid;
        this.password = password;
        this.username = username;
        this.realname = realname;
        this.idnumber = idnumber;
        this.phonenumber = phonenumber;
        this.address = address;
        this.credibility = credibility;
        this.profilephoto = profilephoto;
        this.ischecked = ischecked;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber == null ? null : idnumber.trim();
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getCredibility() {
        return credibility;
    }

    public void setCredibility(Integer credibility) {
        this.credibility = credibility;
    }

    public String getProfilephoto() {
        return profilephoto;
    }

    public void setProfilephoto(String profilephoto) {
        this.profilephoto = profilephoto == null ? null : profilephoto.trim();
    }

    public Integer getIschecked() {
        return ischecked;
    }

    public void setIschecked(Integer ischecked) {
        this.ischecked = ischecked;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userid=").append(userid);
        sb.append(", password=").append(password);
        sb.append(", username=").append(username);
        sb.append(", realname=").append(realname);
        sb.append(", idnumber=").append(idnumber);
        sb.append(", phonenumber=").append(phonenumber);
        sb.append(", address=").append(address);
        sb.append(", credibility=").append(credibility);
        sb.append(", profilephoto=").append(profilephoto);
        sb.append(", ischecked=").append(ischecked);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}