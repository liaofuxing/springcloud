package com.myspringcloud.apigateway.security.config;

import com.myspringcloud.apigateway.security.filter.SmsCodeAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * 短信验证码登录配置器
 *
 * @author liaofuxing
 * @date 2020/02/28 20:13
 */
@Configuration
public class SmsCodeAuthenticationConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler defaultAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler defaultAuthenticationFailureHandler;


    @Override
    public void configure(HttpSecurity http) throws Exception {

        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(defaultAuthenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(defaultAuthenticationFailureHandler);

        http.addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
