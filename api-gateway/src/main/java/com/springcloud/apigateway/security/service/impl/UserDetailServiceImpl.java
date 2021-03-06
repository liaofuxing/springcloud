package com.springcloud.apigateway.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UserDetailServiceImpl implements  SocialUserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;


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
