package com.springcloud.common.enums;

public enum UserTokenEnums {

    TOKEN("token", "token"),

    SYSTEM_SECURITY_TOKEN("运营平台用户token", "SYSTEM_SECURITY_TOKEN:"),
    SYSTEM_USER_INFO("运营平台用户信息", "SYSTEM_USER_INFO:"),

    MALL_SECURITY_TOKEN("商城用户token", "MALL_SECURITY_TOKEN"),
    MALL_USER_INFO("商城用户信息", "MALL_USER_INFO:"),
    ;

    // 成员变量
    private String name;

    private String code;
    // 构造方法
    UserTokenEnums(String name, String code) {
        this.name = name;
        this.code = code;
    }


    // 普通方法
    public static String getName(String code) {
        for (UserTokenEnums s : UserTokenEnums.values()) {
            if (code.equals(s.getCode())) {
                return s.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

