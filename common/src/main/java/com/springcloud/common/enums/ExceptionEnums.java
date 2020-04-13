package com.springcloud.common.enums;

/**
 * 异常类型枚举
 * @author liaofuxing
 * @date 2019/03/10 4:10
 */
public enum ExceptionEnums {
    SYSTEM_EXCEPTION(1, "系统异常"),
    ;

    private Integer code;

    private String message;

    ExceptionEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

