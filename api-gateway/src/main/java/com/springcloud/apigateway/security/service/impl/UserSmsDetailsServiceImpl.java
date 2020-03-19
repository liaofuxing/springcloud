package com.springcloud.apigateway.security.service.impl;

import com.springcloud.apigateway.security.entity.SecurityUser;
import com.springcloud.apigateway.security.service.UserSmsDetailsService;
import com.springcloud.apigateway.securityuser.malluser.dao.MallUserDao;
import com.springcloud.apigateway.securityuser.malluser.entity.MallUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSmsDetailsServiceImpl implements UserSmsDetailsService {

    @Autowired
    private MallUserDao mallUserDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {
        MallUser mallUser = mallUserDao.findSystemUserByPhone(username);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(mallUser == null){
            UsernameNotFoundException usernameNotFoundException = new UsernameNotFoundException("用户名不存在");
            usernameNotFoundException.printStackTrace();
            throw usernameNotFoundException;
        }
        String smsCode = redisTemplate.opsForValue().get("SMS_CODE:"+ username);
        String encode = bCryptPasswordEncoder.encode(smsCode);
        mallUser.setPassword(encode);
        return new SecurityUser(mallUser);
    }

}
