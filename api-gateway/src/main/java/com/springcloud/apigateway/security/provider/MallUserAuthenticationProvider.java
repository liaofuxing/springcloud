package com.springcloud.apigateway.security.provider;

import com.springcloud.apigateway.common.AuthenticationChecks;
import com.springcloud.apigateway.security.service.MallUserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
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
public class MallUserAuthenticationProvider implements AuthenticationProvider {


    private MallUserDetailsService mallUserDetailsService;

    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MallUserAuthenticationToken authenticationToken = (MallUserAuthenticationToken) authentication;
        String username = (String) authenticationToken.getPrincipal();
        // 调用自定义的userDetailsService 查询数据库
        UserDetails userDetails = mallUserDetailsService.loadUserByUsername(username);

        // 校验用户名密码
        AuthenticationChecks.additionalAuthenticationChecks(userDetails, authenticationToken, passwordEncoder);

        //如果user不为空重新构建SmsCodeAuthenticationToken（已认证）
        MallUserAuthenticationToken authenticationResult = new MallUserAuthenticationToken(userDetails, userDetails.getAuthorities());
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
        return MallUserAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public MallUserDetailsService getMallUserDetailsService() {
        return mallUserDetailsService;
    }

    public void setMallUserDetailsService(MallUserDetailsService mallUserDetailsService) {
        this.mallUserDetailsService = mallUserDetailsService;
    }
}
