package com.springcloud.apigateway.security.social.wechat.connet;

import com.springcloud.apigateway.security.social.wechat.api.WeChat;
import com.springcloud.apigateway.security.social.wechat.api.WeChatImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 *
 * 微信登录服务提供者
 *
 * @author liaofuxing
 * @date 2020/02/19 13:27
 * @E-mail liaofuxing@outlook.com
 */
public class WeChatProviderServer extends AbstractOAuth2ServiceProvider<WeChat> {

    private String appId;

    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";

    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    public WeChatProviderServer(String appId, String appSecret) {
        super(new WeChatOAuth2Template(appId, appSecret, URL_AUTHORIZE,URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public WeChat getApi(String accessToken) {

        return new WeChatImpl(accessToken, appId);
    }
}
