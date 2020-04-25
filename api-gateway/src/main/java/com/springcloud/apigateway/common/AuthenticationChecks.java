package com.springcloud.apigateway.common;

import com.springcloud.apigateway.security.provider.SmsCodeAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码校验器
 *
 * @author liaofuxing
 * @date 2020/02/18 11:50
 *
 */
public class AuthenticationChecks {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationChecks.class);

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
            LOGGER.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException("错误的凭证");
        }

        String presentedPassword = authentication.getCredentials().toString();

        if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            LOGGER.debug("Authentication failed: password does not match stored value");

            throw new BadCredentialsException("密码不正确");
        }
    }

    /**
     * 短信验证码校验方法
     *
     * @param authentication  MallUserAuthenticationToken
     * @param redisTemplate  rides 操作工具
     * @throws AuthenticationException 校验失败异常
     */
    public static void smsCodeAuthenticationChecks(SmsCodeAuthenticationToken authentication,
                                                   StringRedisTemplate redisTemplate) throws AuthenticationException {
        // 应该在这里处理登录逻辑
        String smsCode = redisTemplate.opsForValue().get("SMS_CODE:"+ authentication.getPrincipal());
        if (smsCode == null || !smsCode.equals(authentication.getSmsCode())) {
            LOGGER.debug("Authentication failed: smsCode error");

            throw new BadCredentialsException("验证码错误");
        }
    }
}
