package com.springcloud.apigateway.security.filter;

import com.alibaba.fastjson.JSON;
import com.springcloud.common.enums.StatusCodeEnum;
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
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        int status = StatusCodeEnum.CREDENTIALS_EXPIRED.getCode();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(ResultVOUtils.credentials_expired(null)));
        response.flushBuffer();
    }
}
