package com.myspringcloud.apigateway.security.securityhandle;

import com.alibaba.fastjson.JSON;
import com.myspringcloud.apigateway.common.entity.LoginResponseData;
import com.myspringcloud.apigateway.common.entity.ResponseResult;
import com.myspringcloud.apigateway.common.enums.StatusCodeEnum;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
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
        int status = StatusCodeEnum.UNAUTHORIZED.getCode();
        response.setStatus(403);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        ResponseResult<LoginResponseData> responseResult = new ResponseResult(String.valueOf(status),StatusCodeEnum.getName(status), null);
        response.getWriter().print(JSON.toJSONString(responseResult));
        response.flushBuffer();
    }
}
