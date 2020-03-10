package com.springcloud.apigateway.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.springcloud.apigateway.security.entity.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class TokenAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //从请求头中取出token
        String token = request.getHeader("token");
        if(!StringUtils.isEmpty(token)) {
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                //用token从redis中获取用户信息，构造一个SecurityUser
                String userInfoStr = redisTemplate.opsForValue().get("USER_INFO:"+ token);
                Map<String,String> userMap = (Map<String,String>) JSONObject.parse(userInfoStr);
                SecurityUser securityUser = new SecurityUser(userMap.get("username"),userMap.get("password"));
                if (securityUser != null) {
                    //解析并设置认证信息（具体实现不清楚）
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(securityUser, null, securityUser.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
