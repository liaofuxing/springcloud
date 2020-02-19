package com.myspringcloud.apigateway.security.social;

import com.myspringcloud.apigateway.security.social.config.DefaultSpringSocialConfigurer;
import com.myspringcloud.apigateway.security.social.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;


/**
 * @author liaofuxing
 * @date 2019/03/14 19:54
 * @E-mail liaofuxing@outlook.com
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;


    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }




    @Bean
    public SpringSocialConfigurer defaultSpringSocialConfigurer() {
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        DefaultSpringSocialConfigurer configurer = new DefaultSpringSocialConfigurer(filterProcessesUrl);
        configurer.signupUrl(securityProperties.getSocial().getSignUpUrl());
        //configurer.setSocialAuthenticationFilterPostProcessor(socialAuthenticationFilterPostProcessor);
        return configurer;
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());

        //设置表的前缀和数据库一致
        repository.setTablePrefix("social_");
        return repository;
    }



    /**
     * 用来处理注册流程的工具类
     *
     * @param connectionFactoryLocator
     * @return
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator,
                getUsersConnectionRepository(connectionFactoryLocator)) {
        };
    }

}
