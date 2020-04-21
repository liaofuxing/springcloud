package com.springcloud.apigateway.security.config;

import com.springcloud.apigateway.security.filter.SmsCodeAuthenticationFilter;
import com.springcloud.apigateway.security.provider.UserSmsAuthenticationProvider;
import com.springcloud.apigateway.security.service.UserSmsDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
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

//    @Autowired
//    private UserSmsAuthenticationProvider userSmsAuthenticationProvider;
    @Autowired
    private UserSmsDetailsService userSmsDetailsService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 自定义过滤器
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(defaultAuthenticationSuccessHandler);
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler(defaultAuthenticationFailureHandler);

        // 自定义userSmsAuthenticationProvider， 并为Provider 设置 userSmsDetailsService
        UserSmsAuthenticationProvider userSmsAuthenticationProvider = new UserSmsAuthenticationProvider();
        userSmsAuthenticationProvider.setUserSmsDetailsService(userSmsDetailsService);
        userSmsAuthenticationProvider.setRedisTemplate(redisTemplate);
        http.authenticationProvider(userSmsAuthenticationProvider)
                .addFilterAfter(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
