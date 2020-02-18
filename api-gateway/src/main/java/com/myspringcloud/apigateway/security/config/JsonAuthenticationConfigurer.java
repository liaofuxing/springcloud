package com.myspringcloud.apigateway.security.config;

import com.myspringcloud.apigateway.security.filter.JsonAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Json 用户明密码登录配置文件(配置器)
 *
 * @author liaofuxing
 * @date 2020/02/18 11:50
 */
@Configuration
public class JsonAuthenticationConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler defaultAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler defaultAuthenticationFailureHandler;


    @Override
    public void configure(HttpSecurity http) throws Exception {

        JsonAuthenticationFilter jsonAuthenticationFilter = new JsonAuthenticationFilter();
        jsonAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        jsonAuthenticationFilter.setAuthenticationSuccessHandler(defaultAuthenticationSuccessHandler);
        jsonAuthenticationFilter.setAuthenticationFailureHandler(defaultAuthenticationFailureHandler);

        http.addFilterAfter(jsonAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
