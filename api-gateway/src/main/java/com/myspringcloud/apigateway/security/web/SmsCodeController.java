package com.myspringcloud.apigateway.security.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myspringcloud.apigateway.securityuser.entity.MallUser;
import com.myspringcloud.apigateway.securityuser.service.MallUserService;
import com.myspringcloud.common.enums.ResultStatusCodeEnums;
import com.myspringcloud.common.spi.sms.SmsManager;
import com.myspringcloud.common.utils.ResultVOUtils;
import com.myspringcloud.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author: liaofuxing
 * @E-mail: liaofuxing@outlook.com
 * @date: 2020/02/15 14:18
 **/
@Controller
@RequestMapping("/sms")
public class SmsCodeController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/sendSmsCode")
    @ResponseBody
    public String sendSmsCode(@RequestParam String phone, HttpServletResponse response){
        SmsManager smsManager = new SmsManager();
        String smsCode = smsManager.sendSms(phone);
        redisTemplate.opsForValue().set("SMS_CODE:" + phone, smsCode);
        response.setStatus(200);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        ResultVO<MallUser> resultVO = ResultVOUtils.success(smsCode);
        return JSON.toJSONString(resultVO);
    }

}
