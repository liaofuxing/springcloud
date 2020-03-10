package com.springcloud.apigateway.security.social.config;

import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.core.env.Environment;

/**
 * Spring boot 1.5.x SocialAutoConfigurerAdapter 源码，2.x被移除，自己重新从1.5.x中抽取
 * @author liaofuxing
 * @date 2019/03/15 0:18
 * @E-mail liaofuxing@outlook.com
 */
public abstract class SocialAutoConfigurerAdapter extends SocialConfigurerAdapter {
    public SocialAutoConfigurerAdapter() {

    }
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
        configurer.addConnectionFactory(this.createConnectionFactory());
    }
    protected abstract ConnectionFactory<?> createConnectionFactory();
}
