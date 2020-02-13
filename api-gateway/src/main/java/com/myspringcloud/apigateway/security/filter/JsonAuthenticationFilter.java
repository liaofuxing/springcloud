package com.myspringcloud.apigateway.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * json 登录 filter
 */
public class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 获取json数据格式的用户名和密码
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equalsIgnoreCase(HttpMethod.POST.toString())) {
            // 不支持的请求方式
            throw new AuthenticationServiceException("不支持的请求方式: " + request.getMethod());
        }
        if (!MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(request.getContentType())
                && !MediaType.APPLICATION_JSON_UTF8_VALUE.equalsIgnoreCase(request.getContentType())) {
            throw new AuthenticationServiceException("不支持的请求内容格式: " + request.getContentType());
        }
        // 解析request内容
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setExpandEntityReferences(false);
        StringBuffer sb = new StringBuffer();
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
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }
}