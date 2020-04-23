package com.springcloud.apigateway.common;

import com.springcloud.apigateway.security.provider.SmsCodeAuthenticationToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthenticationChecks {

    protected static final Log logger = LogFactory.getLog(AuthenticationChecks.class);

    /**
     * 默认的密码校验方法
     *
     * @param userDetails     用户信息
     * @param authentication  MallUserAuthenticationToken
     * @param passwordEncoder 密码加密规则
     * @throws AuthenticationException
     */
    public static void additionalAuthenticationChecks(UserDetails userDetails,
                                                      AbstractAuthenticationToken authentication,
                                                      PasswordEncoder passwordEncoder)
            throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException("错误的凭证");
        }

        String presentedPassword = authentication.getCredentials().toString();

        if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            logger.debug("Authentication failed: password does not match stored value");

            throw new BadCredentialsException("密码不正确");
        }
    }

    /**
     * 短信验证码校验方法
     *
     * @param authentication  MallUserAuthenticationToken
     * @param redisTemplate  rides 操作工具
     * @throws AuthenticationException
     */
    public static void smsCodeAuthenticationChecks(SmsCodeAuthenticationToken authentication,
                                                   StringRedisTemplate redisTemplate) throws AuthenticationException {
        // 应该在这里处理登录逻辑
        String smsCode = redisTemplate.opsForValue().get("SMS_CODE:"+ authentication.getPrincipal());
        if (smsCode == null || !smsCode.equals(authentication.getSmsCode())) {
            throw new InternalAuthenticationServiceException("验证码错误");
        }
    }
}
