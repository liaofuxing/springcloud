package com.springcloud.apigateway.security.filter;

import com.alibaba.fastjson.JSON;
import com.springcloud.common.enums.StatusCodeEnum;
import com.springcloud.common.utils.ResultVOUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户未登录
 */
@Component
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        int status = StatusCodeEnum.NOT_LOGIN.getCode();
        response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(ResultVOUtils.not_login(null)));
        response.flushBuffer();
        throw new AuthenticationServiceException(StatusCodeEnum.getName(status));

    }
}
