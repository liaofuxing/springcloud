package com.springcloud.apigateway.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.springcloud.apigateway.security.entity.SecurityUser;
import com.springcloud.common.enums.UserTokenEnums;
import com.springcluod.rediscore.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.lang.NonNull;
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
import java.util.concurrent.TimeUnit;

@Component
public class TokenAuthorizationFilter extends OncePerRequestFilter {


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain chain) throws IOException, ServletException {
        //从请求头中取出token
        String token = request.getHeader("token");
        UsernamePasswordAuthenticationToken authentication;
        if (!StringUtils.isEmpty(token)) {
            //用token从redis中获取用户信息，构造一个SecurityUser
            RedisUtils redisUtils = new RedisUtils(redisTemplate);
            String userInfoStr = redisUtils.get(UserTokenEnums.USER_INFO.getCode() + token);
            if (userInfoStr != null) {
                Map<String, String> userMap = (Map<String, String>) JSONObject.parse(userInfoStr);
                SecurityUser securityUser = new SecurityUser(userMap.get("username"), userMap.get("password"));
                // redis 中存在用户信息,将凭证有效时间延长
                redisUtils.expire(UserTokenEnums.USER_INFO.getCode() + token, 30, TimeUnit.MINUTES);
                redisUtils.expire(UserTokenEnums.SECURITY_TOKEN.getCode() + userMap.get("username"), 30, TimeUnit.MINUTES);
                // 设置一个已认证的 authentication
                authentication = new UsernamePasswordAuthenticationToken(securityUser,
                        null, securityUser.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            } else {
                //redis 中不存在用户信息将授权信息设置为空
                authentication = null;
            }
        } else {
            // 请求头中不包含token
            authentication = null;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}
