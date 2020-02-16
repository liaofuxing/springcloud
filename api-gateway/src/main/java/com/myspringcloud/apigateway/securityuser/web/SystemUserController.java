package com.myspringcloud.apigateway.securityuser.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myspringcloud.apigateway.securityuser.entity.MallUser;
import com.myspringcloud.apigateway.securityuser.service.MallUserService;
import com.myspringcloud.common.enums.ResultStatusCodeEnums;
import com.myspringcloud.common.utils.ResultVOUtils;
import com.myspringcloud.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author: liaofuxing
 * @E-mail: liaofuxing@outlook.com
 * @date: 2020/02/15 14:18
 **/
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
        ResultVO<MallUser> resultVO = new ResultVO<>();
        if(!StringUtils.isEmpty(userInfoStr)){
            JSONObject jsonObject = JSONObject.parseObject(userInfoStr);
            MallUser mallUser = JSON.toJavaObject(jsonObject, MallUser.class);
            response.setStatus(200);
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            resultVO = ResultVOUtils.success(mallUser);
        }else {
            //redis中没有用户信息
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            ResultVOUtils.error(null);
            throw new RuntimeException(ResultStatusCodeEnums.ERROR.getMessage());
        }
        return JSON.toJSONString(resultVO);
    }

}