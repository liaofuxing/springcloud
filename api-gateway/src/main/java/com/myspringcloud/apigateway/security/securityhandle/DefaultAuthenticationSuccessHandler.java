package com.myspringcloud.apigateway.security.securityhandle;

import com.alibaba.fastjson.JSON;
import com.myspringcloud.apigateway.common.entity.LoginResponseData;
import com.myspringcloud.apigateway.security.entity.SecurityUser;
import com.myspringcloud.common.spi.sms.SmsManager;
import com.myspringcloud.common.utils.ResultVOUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;


/**
 * 登录成功处理
 */
@Component
public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        String token = UUID.randomUUID().toString();
        String userInfoJsonStr = JSON.toJSONString(user);
        //将用户信息存入存入redis
        stringRedisTemplate.opsForValue().set("USER_INFO:" + token, userInfoJsonStr);
        stringRedisTemplate.opsForValue().set("SECURITY_TOKEN:" + token, user.getUsername());
        response.setStatus(200);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        LoginResponseData responseData = new LoginResponseData(user.getUsername(), token);
        response.getWriter().print(JSON.toJSONString(ResultVOUtils.login_success(responseData)));
        SmsManager smsManager = new SmsManager();
        smsManager.sendSms("132975800233");
        response.flushBuffer();
    }
}
