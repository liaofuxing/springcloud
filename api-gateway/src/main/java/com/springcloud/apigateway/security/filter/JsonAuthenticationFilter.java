package com.springcloud.apigateway.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
 * Json 用户名密码登录过滤器
 *
 * @author liaofuxing
 * @date 2020/02/18 11:50
 */
public class JsonAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public JsonAuthenticationFilter() {
		super(new AntPathRequestMatcher("/user/login", "POST"));
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

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

			return this.getAuthenticationManager().authenticate(authenticationToken);
		}

}
