package com.springcloud.apigateway.security.config;

import com.springcloud.apigateway.security.filter.JsonAuthenticationFilter;
import com.springcloud.apigateway.security.provider.SystemUserAuthenticationProvider;
import com.springcloud.apigateway.security.service.SystemUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Json 用户名密码登录配置文件(配置器)
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

    @Autowired
    private SystemUserDetailsService systemUserDetailsServiceImpl;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 自定义过滤器
        JsonAuthenticationFilter jsonAuthenticationFilter = new JsonAuthenticationFilter();
        jsonAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        jsonAuthenticationFilter.setAuthenticationSuccessHandler(defaultAuthenticationSuccessHandler);
        jsonAuthenticationFilter.setAuthenticationFailureHandler(defaultAuthenticationFailureHandler);

        // 自定义systemUserAuthenticationProvider， 并为Provider 设置 systemUserDetailsService
        SystemUserAuthenticationProvider systemUserAuthenticationProvider = new SystemUserAuthenticationProvider();
        systemUserAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        systemUserAuthenticationProvider.setSystemUserDetailsService(systemUserDetailsServiceImpl);
        http.authenticationProvider(systemUserAuthenticationProvider)
            .addFilterAfter(jsonAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
