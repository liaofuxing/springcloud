package com.myspringcloud.apigateway.security.securityhandle;

import com.alibaba.fastjson.JSON;
import com.myspringcloud.common.enums.StatusCodeEnum;
import com.myspringcloud.common.utils.ResultVOUtils;
import org.springframework.http.MediaType;
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
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().print(JSON.toJSONString(ResultVOUtils.login_failure(null)));
        response.flushBuffer();
        throw new AuthenticationServiceException(StatusCodeEnum.getName(status));
    }
}
