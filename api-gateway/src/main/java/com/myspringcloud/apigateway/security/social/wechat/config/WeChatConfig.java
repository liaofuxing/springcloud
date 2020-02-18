package com.myspringcloud.apigateway.security.social.wechat.config;


import com.myspringcloud.apigateway.security.social.SocialConfig;
import com.myspringcloud.apigateway.security.social.properties.SecurityProperties;
import com.myspringcloud.apigateway.security.social.properties.WeChatProperties;
import com.myspringcloud.apigateway.security.social.wechat.connet.WeChatConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.AuthenticationNameUserIdSource;

import javax.sql.DataSource;

/**
 * @author liaofuxing
 * @date 2019/03/14 22:34
 * @E-mail liaofuxing@outlook.com
 */
@Configuration
public class WeChatConfig extends SocialConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    private ConnectionFactoryRegistry connectionFactoryRegistry = new ConnectionFactoryRegistry();


    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
        configurer.addConnectionFactory(this.createConnectionFactory());
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());

        //设置表的前缀和数据库一致
        repository.setTablePrefix("imooc_");
        return repository;
    }

    protected ConnectionFactory<?> createConnectionFactory() {
        WeChatProperties weChatProperties = securityProperties.getSocial().getWeChat();
        WeChatConnectionFactory weChatConnectionFactory = new WeChatConnectionFactory(weChatProperties.getProviderId(), weChatProperties.getAppId(), weChatProperties.getAppSecret());
        connectionFactoryRegistry.addConnectionFactory(weChatConnectionFactory);
        return weChatConnectionFactory;
    }


    @Bean
    public ProviderSignInUtils providerSignInUtils() {
        return new ProviderSignInUtils(connectionFactoryRegistry, getUsersConnectionRepository(connectionFactoryRegistry));
    }

}
