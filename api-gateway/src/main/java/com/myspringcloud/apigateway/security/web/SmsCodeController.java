package com.myspringcloud.apigateway.security.web;

import com.alibaba.fastjson.JSON;
import com.myspringcloud.apigateway.securityuser.entity.MallUser;
import com.myspringcloud.common.spi.sms.SmsManager;
import com.myspringcloud.common.utils.ResultVOUtils;
import com.myspringcloud.common.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


/**
 * @author: liaofuxing
 * @E-mail: liaofuxing@outlook.com
 * @date: 2020/02/15 14:18
 **/
@Controller
@RequestMapping("/sms")
public class SmsCodeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/sendSmsCode")
    @ResponseBody
    public String sendSmsCode(@RequestParam String phone, HttpServletResponse response){
        logger.info("进入sendSmsCode,phone:{}",phone);
        SmsManager smsManager = new SmsManager();
        String smsCode = smsManager.sendSms(phone);
        redisTemplate.opsForValue().set("SMS_CODE:" + phone, smsCode);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ResultVO<MallUser> resultVO = ResultVOUtils.success(smsCode);
        return JSON.toJSONString(resultVO);
    }

}
