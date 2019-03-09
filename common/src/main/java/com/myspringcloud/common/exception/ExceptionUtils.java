package com.myspringcloud.common.exception;

import com.myspringcloud.common.enums.ExceptionEnums;

/**
 *
 * 异常工具类
 * @author liaofuxing
 * @date 2019/03/10 4:10
 */
public class ExceptionUtils extends RuntimeException{

    private Integer code;

    public ExceptionUtils(Integer code, String message){
        super(message);
        this.code = code;
    }

    public ExceptionUtils(ExceptionEnums exceptionEnums){
        super(exceptionEnums.getMessage());
        this.code = exceptionEnums.getCode();
    }
}
