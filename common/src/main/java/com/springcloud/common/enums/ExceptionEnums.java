package com.springcloud.common.enums;

import lombok.Getter;

/**
 * 异常类型枚举
 * @author liaofuxing
 * @date 2019/03/10 4:10
 */
@Getter
public enum ExceptionEnums {
    SYSTEM_EXCEPTION(1, "系统异常"),
    ;

    private Integer code;

    private String message;

    ExceptionEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }}

