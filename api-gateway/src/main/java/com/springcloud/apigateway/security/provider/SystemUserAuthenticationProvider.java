package com.springcloud.apigateway.security.provider;

import com.springcloud.apigateway.common.AuthenticationChecks;
import com.springcloud.apigateway.security.service.SystemUserDetailsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 短信验证码登录验证器
 *
 * @author liaofuxing
 * @date 2020/04/24 01:50
 */
public class SystemUserAuthenticationProvider implements AuthenticationProvider {

    protected final Log logger = LogFactory.getLog(getClass());

    private SystemUserDetailsService systemUserDetailsService;

    private PasswordEncoder passwordEncoder;

    private StringRedisTemplate redisTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        String username = (String) authenticationToken.getPrincipal();
        //调用自定义的userDetailsService认证
        UserDetails userDetails = systemUserDetailsService.loadUserByUsername(username);

        AuthenticationChecks.additionalAuthenticationChecks(userDetails, authenticationToken, passwordEncoder);

        //如果user不为空重新构建UsernamePasswordAuthenticationToken（已认证）
        UsernamePasswordAuthenticationToken authenticationResult = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;

    }


    /**
     * 只有 Authentication 为 MallUserAuthenticationToken 使用此 Provider 认证
     * @param authentication Provider
     *
     * @return boolean
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }


    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public SystemUserDetailsService getSystemUserDetailsService() {
        return systemUserDetailsService;
    }

    public void setSystemUserDetailsService(SystemUserDetailsService systemUserDetailsService) {
        this.systemUserDetailsService = systemUserDetailsService;
    }

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
