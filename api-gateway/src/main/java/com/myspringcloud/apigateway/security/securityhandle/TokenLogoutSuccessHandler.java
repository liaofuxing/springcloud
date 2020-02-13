package com.myspringcloud.apigateway.security.securityhandle;

import com.alibaba.fastjson.JSON;
import com.myspringcloud.apigateway.common.entity.ResponseData;
import com.myspringcloud.apigateway.common.entity.ResponseResult;
import com.myspringcloud.apigateway.common.enums.StatusCodeEnum;
import com.myspringcloud.apigateway.security.entity.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Component
public class TokenLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException , ServletException {
        int status = StatusCodeEnum.LOGOUT_SUCCESS.getCode();
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        String token = redisTemplate.opsForValue().get("SECURITY_USERNAME : " + user.getUsername());
        redisTemplate.expire("SECURITY_USERNAME : " + user.getUsername(), 0, TimeUnit.MICROSECONDS);
        redisTemplate.expire("SECURITY_TOKEN : " + token, 0, TimeUnit.MICROSECONDS);

        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        ResponseResult<ResponseData> responseResult = new ResponseResult(String.valueOf(status), StatusCodeEnum.getName(status), null);
        response.getWriter().print(JSON.toJSONString(responseResult));
        response.flushBuffer();
    }
}

