package com.schoolsell.util;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class WXSessionMode {
    private String session_key;
    private String openid;

    public WXSessionMode() {
    }

    public WXSessionMode(String session_key, String openid) {
        this.session_key = session_key;
        this.openid = openid;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public String getOpenid() {
        return openid;
    }
}
