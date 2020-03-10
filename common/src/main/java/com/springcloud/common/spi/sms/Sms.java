package com.springcloud.common.spi.sms;

public interface Sms {
    String sendSmsCode(String phoneNo);
}
