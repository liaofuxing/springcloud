package com.myspringcloud.apigateway.security.social.properties;

/**
 * @author liaofuxing
 * @date 2019/03/12 19:59
 * @E-mail liaofuxing@outlook.com
 */
public class SmsCodeProperties {


    private int length = 4; //验证码长度

    private int expireIn = 60; //超时时间

    /**
     * 要拦截的url，多个url用逗号隔开，ant pattern
     */
    private String url;


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(int expireIn) {
        this.expireIn = expireIn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
