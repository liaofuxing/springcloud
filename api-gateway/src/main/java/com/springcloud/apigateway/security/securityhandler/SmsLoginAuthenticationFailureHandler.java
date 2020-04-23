package com.springcloud.apigateway.security.securityhandler;

import com.alibaba.fastjson.JSON;
import com.springcloud.common.enums.StatusCodeEnums;
import com.springcloud.common.utils.ResultVOUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 手机号短信登录失败
 */
@Component
public class SmsLoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        if(exception instanceof UsernameNotFoundException){
            response.getWriter().print(JSON.toJSONString(ResultVOUtils.sms_not_find_phone(StatusCodeEnums.SMS_NOT_FIND_PHONE.getName())));
        }else {
            response.getWriter().print(JSON.toJSONString(ResultVOUtils.sms_code_error(StatusCodeEnums.SMS_CODE_ERROR.getName())));
        }
        response.flushBuffer();
    }
}
