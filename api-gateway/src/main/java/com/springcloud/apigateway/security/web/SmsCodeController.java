package com.springcloud.apigateway.security.web;

import com.springcloud.common.spi.sms.SmsManager;
import com.springcloud.common.utils.ResultVOUtils;
import com.springcloud.common.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/02/15 14:18
 **/
@Controller
@RequestMapping("/user")
public class SmsCodeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/sendSmsCode")
    @ResponseBody
    public ResultVO<String> sendSmsCode(@RequestParam String phone){
        logger.info("进入sendSmsCode,phone:{}",phone);
        SmsManager smsManager = new SmsManager();
        String smsCode = smsManager.sendSms(phone);
        redisTemplate.opsForValue().set("SMS_CODE:" + phone, smsCode);
        return ResultVOUtils.success(smsCode);
    }

}
