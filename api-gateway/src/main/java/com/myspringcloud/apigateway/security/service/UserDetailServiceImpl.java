package com.myspringcloud.apigateway.security.service;

import com.myspringcloud.apigateway.security.entity.SecurityUser;
import com.myspringcloud.apigateway.securityuser.dao.MallUserDao;
import com.myspringcloud.apigateway.securityuser.entity.MallUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
public class UserDetailServiceImpl implements UserDetailsService, SocialUserDetailsService {

   @Autowired
   private MallUserDao mallUserDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MallUser mallUser = mallUserDao.findSystemUserByUsername(username);

        if (mallUser == null){
            UsernameNotFoundException usernameNotFoundException = new UsernameNotFoundException("用户名不存在");
            usernameNotFoundException.printStackTrace();
            throw usernameNotFoundException;
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
