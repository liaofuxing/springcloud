package com.myspringcloud.common.enums;

import lombok.Getter;

/**
 * @author liaofuxing
 * @date 2019/03/10 4:39
 * @E-mail liaofuxing@outlook.com
 */
@Getter
public enum ResultStatusCodeEnums {

    OK(200, "成功"),
    ;

    private Integer code;

    private String message;

    ResultStatusCodeEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
