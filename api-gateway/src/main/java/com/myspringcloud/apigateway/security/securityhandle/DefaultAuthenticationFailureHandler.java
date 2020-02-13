package com.myspringcloud.apigateway.security.securityhandle;

import com.alibaba.fastjson.JSON;
import com.myspringcloud.apigateway.common.entity.ResponseData;
import com.myspringcloud.apigateway.common.entity.ResponseResult;
import com.myspringcloud.apigateway.common.enums.StatusCodeEnum;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 授权失败handler
 */
@Component
public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        int status = StatusCodeEnum.LOGIN_FAILURE.getCode();
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        ResponseResult<ResponseData> responseResult = new ResponseResult(String.valueOf(status),StatusCodeEnum.getName(status), null);
        response.getWriter().print(JSON.toJSONString(responseResult));
        response.flushBuffer();
    }
}