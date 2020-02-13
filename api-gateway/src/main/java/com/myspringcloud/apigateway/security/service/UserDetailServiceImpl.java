package com.myspringcloud.apigateway.security.service;

import com.myspringcloud.apigateway.security.entity.SecurityUser;
import com.myspringcloud.apigateway.securityuser.dao.MallUserDao;
import com.myspringcloud.apigateway.securityuser.entity.MallUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

   @Autowired
   private MallUserDao mallUserDao;


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
}
