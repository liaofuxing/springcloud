package com.springcloud.mall.spi.service;

import com.springcloud.common.spi.sms.Sms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsImplA implements Sms {
    private final Logger  LOGGER = LoggerFactory.getLogger(SmsImplA.class);

    @Override
    public String sendSmsCode(String phoneNo) {
        LOGGER.info("发给:{}短信验证码为：90111", phoneNo);
        System.out.println("发给"+phoneNo+"短信验证码为：90111");
        return "1234";
    }
}
