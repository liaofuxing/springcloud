package com.myspringcloud.apigateway.common.spi.service;

import com.myspringcloud.common.spi.sms.Sms;

public class SmsImpl implements Sms {
    @Override
    public void sendSmsCode(String phoneNo) {
        System.out.println("发给"+phoneNo+"短信验证码为：123456");
    }
}
