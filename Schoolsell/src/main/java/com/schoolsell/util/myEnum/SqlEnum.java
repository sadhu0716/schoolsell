package com.schoolsell.util.myEnum;

public enum SqlEnum {
    USER_NOT_EXIST("用户不存在"),
    USER_PASSWD_WRONG("密码错误"),
    USER_ISNULL("用户名为空"),
    USER_PASSWD_ISNULL("密码为空"),
    USER_REGISTER_WRONG("用户注册信息未填写完整"),
    USER_IS_EXIST("用户已存在"),
    USER_UPDATE_WRONG("信息更改不合法，某些信息不可为空"),

    SQL_QUERY_WRONG("查询错误"),
    SQL_INSERT_WRONG("插入错误"),
    SQL_DELETE_WRONG("删除错误"),
    SQL_UPDATE_WRONG("更新错误"),

    COMMODITY_EXIST("商品已存在"),

    USER_PASSWD_RIGHT("密码正确"),
    SQL_SUCCESS("执行成功");

    SqlEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    private String message;


}
