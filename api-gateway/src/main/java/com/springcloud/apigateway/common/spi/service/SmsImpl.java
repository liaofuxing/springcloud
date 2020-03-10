package com.springcloud.apigateway.common.spi.service;

import com.springcloud.common.spi.sms.Sms;

/**
 * 发送短信具体实现类
 */
public class SmsImpl implements Sms {
    @Override
    public String sendSmsCode(String phoneNo) {
        String smsCode = "smsCode1234";
        System.out.println("发给"+phoneNo+"短信验证码为："+smsCode);
        return smsCode;

    }
}
