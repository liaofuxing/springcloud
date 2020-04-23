package com.springcloud.apigateway.security.config;

import com.springcloud.apigateway.security.filter.MallUserAuthenticationFilter;
import com.springcloud.apigateway.security.provider.MallUserAuthenticationProvider;
import com.springcloud.apigateway.security.service.MallUserDetailsService;
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
 * 短信验证码登录配置器
 *
 * @author liaofuxing
 * @date 2020/02/28 20:13
 */
@Configuration
public class MallUserAuthenticationConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler mallUserAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler defaultAuthenticationFailureHandler;

    @Autowired
    private MallUserDetailsService mallUserDetailsServiceImpl;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 自定义过滤器
        MallUserAuthenticationFilter mallUserAuthenticationFilter = new MallUserAuthenticationFilter();
        mallUserAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        mallUserAuthenticationFilter.setAuthenticationSuccessHandler(mallUserAuthenticationSuccessHandler);
        mallUserAuthenticationFilter.setAuthenticationFailureHandler(defaultAuthenticationFailureHandler);

        // 自定义userSmsAuthenticationProvider， 并为Provider 设置 userSmsDetailsService
        MallUserAuthenticationProvider mallUserAuthenticationProvider = new MallUserAuthenticationProvider();
        mallUserAuthenticationProvider.setMallUserDetailsService(mallUserDetailsServiceImpl);
        mallUserAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        http.authenticationProvider(mallUserAuthenticationProvider)
                .addFilterAfter(mallUserAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
