package com.myspringcloud.common.spi.sms;

public interface Sms {
    String sendSmsCode(String phoneNo);
}
