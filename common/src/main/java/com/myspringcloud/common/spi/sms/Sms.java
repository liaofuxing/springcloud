package com.myspringcloud.common.spi.sms;

public interface Sms {
    void sendSmsCode(String phoneNo);
}
