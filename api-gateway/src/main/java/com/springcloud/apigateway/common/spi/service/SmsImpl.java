package com.springcloud.apigateway.common.spi.service;

import com.springcloud.common.spi.sms.Sms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 发送短信具体实现类
 */
public class SmsImpl implements Sms {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsImpl.class);

    @Override
    public String sendSmsCode(String phoneNo) {
        String smsCode = "1234";
        LOGGER.info("发给: {}的短信验证码为：{}",phoneNo, smsCode);

        return smsCode;
    }
}
