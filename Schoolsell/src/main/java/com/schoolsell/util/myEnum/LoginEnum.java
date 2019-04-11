package com.schoolsell.util.myEnum;

public enum LoginEnum {
    LOGIN_SUCCESS("密码正确"),
    LOGIN_PASSWORD_WRONG("密码错误"),
    LOGIN_USERNAME_ISNULL("用户名为空"),
    LOGIN_PASSWORD_ISNULL("密码为空"),
    LOGIN_USER_NOT_EXIST("用户不存在");

    private String message;

    public String getMessage() {
        return message;
    }

    LoginEnum(String message) {

        this.message = message;
    }
}
