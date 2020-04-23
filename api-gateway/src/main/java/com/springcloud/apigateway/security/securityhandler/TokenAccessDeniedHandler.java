package com.springcloud.apigateway.security.securityhandler;

import com.alibaba.fastjson.JSON;
import com.springcloud.common.enums.StatusCodeEnums;
import com.springcloud.common.utils.ResultVOUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException , ServletException {
        int status = StatusCodeEnums.UNAUTHORIZED.getCode();
        response.setStatus(403);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(ResultVOUtils.unauthorized(null)));
        response.flushBuffer();
        throw new AuthenticationServiceException(StatusCodeEnums.getName(status));
    }
}
