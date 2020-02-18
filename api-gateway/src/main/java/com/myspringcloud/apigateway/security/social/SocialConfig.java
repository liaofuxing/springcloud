package com.myspringcloud.apigateway.security.social;

import com.myspringcloud.apigateway.security.social.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.stereotype.Component;


/**
 * @author liaofuxing
 * @date 2019/03/14 19:54
 * @E-mail liaofuxing@outlook.com
 */
@Component
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Bean
    public SpringSocialConfigurer defaultSpringSocialConfigurer() {
        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();
        DefaultSpringSocialConfigurer configurer = new DefaultSpringSocialConfigurer(filterProcessesUrl);
        configurer.signupUrl(securityProperties.getSocial().getSignUpUrl());
        return configurer;
    }

}
