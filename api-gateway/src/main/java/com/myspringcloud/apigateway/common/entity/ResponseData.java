package com.myspringcloud.apigateway.common.entity;

import lombok.Data;

@Data
public class ResponseData {

    private String username;
    private String token;

    public ResponseData() {

    }
    public ResponseData(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
