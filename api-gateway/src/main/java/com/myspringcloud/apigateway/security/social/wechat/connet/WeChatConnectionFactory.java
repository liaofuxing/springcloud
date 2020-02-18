package com.myspringcloud.apigateway.security.social.wechat.connet;

import com.myspringcloud.apigateway.security.social.wechat.api.WeChat;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @author liaofuxing
 * @date 2019/03/14 19:45
 * @E-mail liaofuxing@outlook.com
 */
public class WeChatConnectionFactory extends OAuth2ConnectionFactory<WeChat> {

    public WeChatConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new WeChatProviderServer(appId, appSecret), new WeChatAdapter());
    }
}
