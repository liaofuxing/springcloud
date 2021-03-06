package com.springcloud.apigateway.security.securityhandler;

import com.alibaba.fastjson.JSON;
import com.springcloud.common.enums.StatusCodeEnums;
import com.springcloud.common.utils.ResultVOUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 凭证失效
 */
@Component
public class TokenAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        int status = StatusCodeEnums.CREDENTIALS_EXPIRED.getCode();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(ResultVOUtils.credentials_expired(StatusCodeEnums.getName(status))));
        response.flushBuffer();
    }
}
