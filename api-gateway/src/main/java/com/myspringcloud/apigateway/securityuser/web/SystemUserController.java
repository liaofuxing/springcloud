package com.myspringcloud.apigateway.securityuser.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myspringcloud.apigateway.securityuser.entity.MallUser;
import com.myspringcloud.apigateway.securityuser.service.MallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class SystemUserController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private MallUserService mallUserService;

    @PostMapping("/info")
    @ResponseBody
    public MallUser getUserInfoByToken(HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader("token");
        String userInfoStr = stringRedisTemplate.opsForValue().get("USER_INFO:"+ token);
        JSONObject jsonObject = JSONObject.parseObject(userInfoStr);
        MallUser mallUser = JSON.toJavaObject(jsonObject, MallUser.class);
        return mallUser;
    }

}
