package com.springcloud.apigateway.common.entity;

import lombok.Data;

@Data
public class LoginResponseData {

    private String username;
    private String token;

    public LoginResponseData() {

    }
    public LoginResponseData(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
