package com.springcloud.apigateway.security.securityhandle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springcloud.apigateway.securityuser.systemuser.entity.SystemUser;
import com.springcloud.common.utils.ResultVOUtils;
import com.springcluod.rediscore.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Component
public class TokenLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException , ServletException {
        RedisUtils redisUtils = new RedisUtils(stringRedisTemplate);
        String requestToken = request.getHeader("token");
        String userInfoStr = redisUtils.get("USER_INFO:" + requestToken);

        SystemUser user = JSON.toJavaObject( JSONObject.parseObject(userInfoStr), SystemUser.class);

        String token = redisUtils.get("SECURITY_TOKEN:" + user.getUsername());
        // 将redis 上的缓存信息设置为即将过期
        redisUtils.expire("USER_INFO:" + token, 0 , TimeUnit.MICROSECONDS);
        redisUtils.expire("SECURITY_TOKEN:" + token, 0 , TimeUnit.MICROSECONDS);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(JSON.toJSONString(ResultVOUtils.logout_success(null)));
        response.flushBuffer();
    }
}

