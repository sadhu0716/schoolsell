package com.schoolsell.entity;

import java.io.Serializable;

public class Chatreport implements Serializable {
    private Integer chatid;

    private String sendid;

    private String acceptid;

    private String context;

    private String date;

    private Boolean isbrowse;

    private static final long serialVersionUID = 1L;

    public Integer getChatid() {
        return chatid;
    }

    public void setChatid(Integer chatid) {
        this.chatid = chatid;
    }

    public String getSendid() {
        return sendid;
    }

    public void setSendid(String sendid) {
        this.sendid = sendid == null ? null : sendid.trim();
    }

    public String getAcceptid() {
        return acceptid;
    }

    public void setAcceptid(String acceptid) {
        this.acceptid = acceptid == null ? null : acceptid.trim();
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public Boolean getIsbrowse() {
        return isbrowse;
    }

    public void setIsbrowse(Boolean isbrowse) {
        this.isbrowse = isbrowse;
    }

    public Chatreport(Integer chatid, String sendid, String acceptid, String context, String date, Boolean isbrowse) {
        this.chatid = chatid;
        this.sendid = sendid;
        this.acceptid = acceptid;
        this.context = context;
        this.date = date;
        this.isbrowse = isbrowse;
    }

    public Chatreport(String sendid, String acceptid, String context, String date, Boolean isbrowse) {
        this.sendid = sendid;
        this.acceptid = acceptid;
        this.context = context;
        this.date = date;
        this.isbrowse = isbrowse;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", chatid=").append(chatid);
        sb.append(", sendid=").append(sendid);
        sb.append(", acceptid=").append(acceptid);
        sb.append(", context=").append(context);
        sb.append(", date=").append(date);
        sb.append(", isbrowse=").append(isbrowse);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}