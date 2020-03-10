
package com.springcloud.apigateway.security.social.wechat.connet;

import com.springcloud.apigateway.security.social.wechat.api.WeChat;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 *
 * 微信登录服务链接工厂
 *
 * @author liaofuxing
 * @date 2020/02/19 13:27
 * @E-mail liaofuxing@outlook.com
 */
public class WeChatConnectionFactory extends OAuth2ConnectionFactory<WeChat> {

    public WeChatConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new WeChatProviderServer(appId, appSecret), new WeChatAdapter());
    }

    /**
     * 由于微信的openId是和accessToken一起返回的，所以在这里直接根据accessToken设置providerUserId即可，不用像QQ那样通过QQAdapter来获取
     *
     * @param accessGrant
     * @return
     */
    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        if (accessGrant instanceof WeChatAccessGrant) {
            return ((WeChatAccessGrant) accessGrant).getOpenid();
        }
        return null;
    }

    /**
     * @param accessGrant
     * @return
     */
    public Connection<WeChat> createConnection(AccessGrant accessGrant) {
        return new OAuth2Connection<WeChat>(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(),
                accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(), getApiAdapter(extractProviderUserId(accessGrant)));
    }

    /**
     * @param data
     * @return
     */
    public Connection<WeChat> createConnection(ConnectionData data) {
        return new OAuth2Connection<WeChat>(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
    }

    /**
     * @param providerUserId
     * @return
     */
    private ApiAdapter<WeChat> getApiAdapter(String providerUserId) {
        return new WeChatAdapter(providerUserId);
    }

    /**
     * @return
     */
    private OAuth2ServiceProvider<WeChat> getOAuth2ServiceProvider() {
        return (OAuth2ServiceProvider<WeChat>) getServiceProvider();
    }


}
