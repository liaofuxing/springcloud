package com.springcloud.apigateway.security.service.impl;

import com.springcloud.apigateway.security.entity.SecurityUser;
import com.springcloud.apigateway.security.service.MallUserDetailsService;
import com.springcloud.apigateway.securityuser.malluser.dao.MallUserDao;
import com.springcloud.apigateway.securityuser.malluser.entity.MallUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MallDetailsServiceImpl implements MallUserDetailsService {

    @Autowired
    private MallUserDao mallUserDao;


    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {
        MallUser mallUser = mallUserDao.findMallUserByUsername(username);

        if (mallUser == null){
            UsernameNotFoundException usernameNotFoundException = new UsernameNotFoundException("用户名不存在");
            usernameNotFoundException.printStackTrace();
            throw usernameNotFoundException;
        }

        return new SecurityUser(mallUser);
    }
}
