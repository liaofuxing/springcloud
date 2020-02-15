package com.myspringcloud.apigateway.security.filter;

import com.alibaba.fastjson.JSON;
import com.myspringcloud.apigateway.common.entity.ResponseResult;
import com.myspringcloud.apigateway.common.enums.StatusCodeEnum;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        int status = StatusCodeEnum.NOT_LOGIN.getCode();
        response.setStatus(200);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        ResponseResult responseResult = new ResponseResult(status, StatusCodeEnum.getName(status), null);
        response.getWriter().print(JSON.toJSONString(responseResult));
        response.flushBuffer();
        throw new AuthenticationServiceException(StatusCodeEnum.getName(status));
    }
}
