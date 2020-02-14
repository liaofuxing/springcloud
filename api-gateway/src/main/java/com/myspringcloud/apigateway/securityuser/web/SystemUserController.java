package com.myspringcloud.apigateway.securityuser.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myspringcloud.apigateway.common.entity.ResponseResult;
import com.myspringcloud.apigateway.common.enums.StatusCodeEnum;
import com.myspringcloud.apigateway.securityuser.entity.MallUser;
import com.myspringcloud.apigateway.securityuser.service.MallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/info")
    @ResponseBody
    public String getUserInfoByToken(HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader("token");
        String userInfoStr = stringRedisTemplate.opsForValue().get("USER_INFO:"+ token);
        JSONObject jsonObject = JSONObject.parseObject(userInfoStr);
        MallUser mallUser = JSON.toJavaObject(jsonObject, MallUser.class);
        response.setStatus(200);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        ResponseResult<MallUser> responseResult = new ResponseResult(String.valueOf(StatusCodeEnum.SUCCESS.getCode()), StatusCodeEnum.getName(StatusCodeEnum.SUCCESS.getCode()), mallUser);
        JSON.toJSONString(responseResult);
        return JSON.toJSONString(responseResult);
    }

}
