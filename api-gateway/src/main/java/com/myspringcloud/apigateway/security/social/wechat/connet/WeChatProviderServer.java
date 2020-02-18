package com.myspringcloud.apigateway.security.social.wechat.connet;

import com.myspringcloud.apigateway.security.social.wechat.api.WeChat;
import com.myspringcloud.apigateway.security.social.wechat.api.WeChatImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @author liaofuxing
 * @date 2019/03/14 19:07
 * @E-mail liaofuxing@outlook.com
 */
public class WeChatProviderServer extends AbstractOAuth2ServiceProvider<WeChat> {

    private String appId;

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESSTOKEN = "https://graph.qq.com/oauth2.0/token";

    public WeChatProviderServer(String appId, String appSecret) {
        super(new WeChatOAuth2Template(appId, appSecret, URL_AUTHORIZE,URL_ACCESSTOKEN));
        this.appId = appId;
    }

    @Override
    public WeChat getApi(String accessToken) {

        return new WeChatImpl(accessToken, appId);
    }
}
