package com.springcloud.apigateway.security.securityhandler;

import com.alibaba.fastjson.JSON;
import com.springcloud.apigateway.security.entity.SecurityUser;
import com.springcloud.apigateway.securityuser.systemuser.entity.SystemUser;
import com.springcloud.apigateway.securityuser.systemuser.service.SystemUserService;
import com.springcloud.common.entity.LoginResponseData;
import com.springcloud.common.utils.ResultVOUtils;
import com.springcluod.rediscore.utils.RedisUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * 登录成功处理
 */
@Component
public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private SystemUserService systemUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
           RedisUtils redisUtils = new RedisUtils(stringRedisTemplate);
           SecurityUser user = (SecurityUser) authentication.getPrincipal();
           String token = UUID.randomUUID().toString();

           SystemUser systemUserByUsername = systemUserService.findSystemUserByUsername(user.getUsername());
           String userInfoJsonStr = JSON.toJSONString(systemUserByUsername);
           //将用户信息存入存入redis
           redisUtils.setEx("USER_INFO:" + token, userInfoJsonStr,30, TimeUnit.MINUTES);
           redisUtils.setEx("SECURITY_TOKEN:" + user.getUsername(), token,30, TimeUnit.MINUTES);
           response.setStatus(200);
           response.setContentType("application/json;charset=UTF-8");
           LoginResponseData responseData = new LoginResponseData(user.getUsername(), token);
           response.getWriter().print(JSON.toJSONString(ResultVOUtils.login_success(responseData)));
           response.flushBuffer();
    }
}
