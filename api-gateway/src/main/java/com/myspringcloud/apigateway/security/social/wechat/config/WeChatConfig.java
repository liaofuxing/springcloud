package com.myspringcloud.apigateway.security.social.wechat.config;


import com.myspringcloud.apigateway.security.social.config.SocialAutoConfigurerAdapter;
import com.myspringcloud.apigateway.security.social.properties.SecurityProperties;
import com.myspringcloud.apigateway.security.social.properties.WeChatProperties;
import com.myspringcloud.apigateway.security.social.wechat.connet.WeChatConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * 微信登录配置
 * @author liaofuxing
 * @date 2020/02/19 22:34
 * @E-mail liaofuxing@outlook.com
 */
@Configuration
@ConditionalOnProperty(prefix = "spring-social.security.social.we-chat", name = "app-id")
public class WeChatConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ConnectionFactory<?> createConnectionFactory() {
        WeChatProperties weChatProperties = securityProperties.getSocial().getWeChat();
        return new WeChatConnectionFactory(weChatProperties.getProviderId(), weChatProperties.getAppId(),
                weChatProperties.getAppSecret());
    }

}
