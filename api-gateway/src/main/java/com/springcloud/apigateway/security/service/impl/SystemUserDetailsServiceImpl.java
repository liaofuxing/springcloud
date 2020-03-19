package com.springcloud.apigateway.security.service.impl;

import com.springcloud.apigateway.security.entity.SecurityUser;
import com.springcloud.apigateway.security.service.SystemUserDetailsService;
import com.springcloud.apigateway.securityuser.systemuser.dao.SystemUserDao;
import com.springcloud.apigateway.securityuser.systemuser.entity.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SystemUserDetailsServiceImpl implements SystemUserDetailsService {

    @Autowired
    private SystemUserDao systemUserDao;

    @Override
    public SecurityUser loadUserBySystemUsername(String username) throws UsernameNotFoundException {
        SystemUser  systemUser = systemUserDao.findSystemUserByUsername(username);
            if (systemUser == null){
                UsernameNotFoundException usernameNotFoundException = new UsernameNotFoundException("用户名不存在");
                usernameNotFoundException.printStackTrace();
                throw usernameNotFoundException;
            }
        return new SecurityUser(systemUser);
    }
}
