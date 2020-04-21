package com.springcloud.apigateway.security.provider;

import com.springcloud.apigateway.security.service.UserSmsDetailsService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSmsAuthenticationProvider implements AuthenticationProvider {


    private UserSmsDetailsService userSmsDetailsService;

    private StringRedisTemplate redisTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        //调用自定义的userDetailsService认证
        UserDetails user = userSmsDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());

        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
        // 应该在这里处理登录逻辑
        String smsCode = redisTemplate.opsForValue().get("SMS_CODE:"+ authenticationToken.getPrincipal());
        if (smsCode == null || !smsCode.equals(authenticationToken.getSmsCode())) {
            throw new InternalAuthenticationServiceException("验证码错误");
        }

        //如果user不为空重新构建SmsCodeAuthenticationToken（已认证）
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;

    }

    /**
     * 只有Authentication为SmsCodeAuthenticationToken使用此Provider认证
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserSmsDetailsService getUserSmsDetailsService() {
        return userSmsDetailsService;
    }

    public void setUserSmsDetailsService(UserSmsDetailsService userSmsDetailsService) {
        this.userSmsDetailsService = userSmsDetailsService;
    }

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
