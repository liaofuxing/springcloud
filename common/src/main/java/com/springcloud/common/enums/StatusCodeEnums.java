package com.springcloud.common.enums;

/**
 * 返回状态码
 */
public enum StatusCodeEnums {
    LOGIN_SUCCESS("登录成功", 20000),
    SUCCESS("请求成功", 20001),
    LOGIN_FAILURE("用户名或密码错误", 50001),
    LOGOUT_SUCCESS("注销成功",50002),
    CREDENTIALS_EXPIRED("凭证过期",50003),
    ERROR("系统出错", 50000),
    UNAUTHORIZED("未授权", 50004),

    SMS_NOT_FIND_PHONE("手机号不正确",50006),
    SMS_CODE_ERROR("验证码错误",50007);
    // 成员变量
    private String name;
    private int code;
    // 构造方法
    StatusCodeEnums(String name, int code) {
        this.name = name;
        this.code = code;
    }
    // 普通方法
    public static String getName(int code) {
        for (StatusCodeEnums s : StatusCodeEnums.values()) {
            if (s.getCode() == code) {
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
