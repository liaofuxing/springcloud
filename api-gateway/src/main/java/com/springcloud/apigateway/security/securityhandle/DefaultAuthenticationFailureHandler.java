package com.springcloud.apigateway.security.securityhandle;

import com.alibaba.fastjson.JSON;
import com.springcloud.common.enums.StatusCodeEnum;
import com.springcloud.common.utils.ResultVOUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
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
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(ResultVOUtils.login_failure(null)));
        response.flushBuffer();
        throw new AuthenticationServiceException(StatusCodeEnum.getName(status));
    }
}