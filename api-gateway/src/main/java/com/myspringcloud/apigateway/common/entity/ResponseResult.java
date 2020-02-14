package com.myspringcloud.apigateway.common.entity;

import lombok.Data;

@Data
public class ResponseResult<T> {

    private Integer code;
    private String msg;
    private T data;

    public ResponseResult (){

    }

    public ResponseResult(Integer code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
