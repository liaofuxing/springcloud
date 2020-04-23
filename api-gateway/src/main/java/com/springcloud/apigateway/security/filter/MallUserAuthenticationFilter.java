package com.springcloud.apigateway.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springcloud.apigateway.security.provider.MallUserAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 短信验证码登录过滤器
 *
 * @author liaofuxing
 * @date 2020/02/28 20:21
 */
public class MallUserAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public MallUserAuthenticationFilter() {
        super(new AntPathRequestMatcher("/user/mallUserLogin", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setExpandEntityReferences(false);
        StringBuilder sb = new StringBuilder();
        try (InputStream inputStream = request.getInputStream(); BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                sb.append(str);
            }
        } catch (IOException ex) {
            throw new RuntimeException("获取请求内容异常", ex);
        }

        JSONObject jsonObject = JSON.parseObject(sb.toString());
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");


        //创建SmsCodeAuthenticationToken(未认证)
        MallUserAuthenticationToken authenticationToken = new MallUserAuthenticationToken(username, password);
        //设置用户信息
        setDetails(request, authenticationToken);
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

    protected void setDetails(HttpServletRequest request, MallUserAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

}
