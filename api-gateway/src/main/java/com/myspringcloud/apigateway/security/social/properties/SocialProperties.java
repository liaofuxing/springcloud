package com.myspringcloud.apigateway.security.social.properties;

/**
 * @author liaofuxing
 * @date 2019/03/14 22:31
 * @E-mail liaofuxing@outlook.com
 */
public class SocialProperties {

    private WeChatProperties weChat;

    private QQProperties qq;
    private String filterProcessesUrl;
    private String signUpUrl;

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }

    public WeChatProperties getWeChat() {
        return weChat;
    }

    public void setWeChat(WeChatProperties weChat) {
        this.weChat = weChat;
    }

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }
}
