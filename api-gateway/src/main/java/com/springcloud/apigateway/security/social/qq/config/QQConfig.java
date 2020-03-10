package com.springcloud.apigateway.security.social.qq.config;


import com.springcloud.apigateway.security.social.config.SocialAutoConfigurerAdapter;
import com.springcloud.apigateway.security.social.properties.QQProperties;
import com.springcloud.apigateway.security.social.properties.SecurityProperties;
import com.springcloud.apigateway.security.social.qq.connet.QQConnectionFactory;
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
@ConditionalOnProperty(prefix = "spring-social.security.social.qq", name = "app-id")
public class QQConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }
}
