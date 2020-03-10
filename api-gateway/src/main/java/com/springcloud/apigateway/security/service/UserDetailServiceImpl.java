package com.springcloud.apigateway.security.service;

import com.springcloud.apigateway.security.entity.SecurityUser;
import com.springcloud.apigateway.securityuser.dao.MallUserDao;
import com.springcloud.apigateway.securityuser.entity.MallUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;


/**
 *
 * @author liaofuxing
 * @date 2020/02/18 11:50
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService, SocialUserDetailsService {

   @Autowired
   private MallUserDao mallUserDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MallUser mallUser = mallUserDao.findSystemUserByUsername(username);

        if (mallUser == null){
            //使用phone登录，ps：使用手机话和验证码登录
            //如果username查询user为空，尝试使用手机号查询user
            mallUser = mallUserDao.findSystemUserByPhone(username);
            if(mallUser == null){
                UsernameNotFoundException usernameNotFoundException = new UsernameNotFoundException("用户名不存在");
                usernameNotFoundException.printStackTrace();
                throw usernameNotFoundException;
            }
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            //1. 根据前端的手机号在redis上查找验证码,按照bCryptPasswordEncoder方式加密，
            //2. 将smsCode赋值给password
            //3. 模拟username，password方式验证
            String smsCode = redisTemplate.opsForValue().get("SMS_CODE:"+ username);
            String encode = bCryptPasswordEncoder.encode(smsCode);
            mallUser.setPassword(encode);

        }
        SecurityUser securityUser = new SecurityUser(mallUser);
        return securityUser;
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {

        return buildUser(userId);
    }

    private SocialUserDetails buildUser(String userId) {
        // 根据用户名查找用户信息
        //根据查找到的用户信息判断用户是否被冻结
        String password = passwordEncoder.encode("123456");

        return new SocialUser(userId, password,
                true, true, true, true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("xxx"));
    }
}
