package com.springcloud.common.enums;

public enum UserTokenEnums {
    SECURITY_TOKEN("用户token", "SECURITY_TOKEN:"),

    USER_INFO("用户信息", "USER_INFO:"),
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

