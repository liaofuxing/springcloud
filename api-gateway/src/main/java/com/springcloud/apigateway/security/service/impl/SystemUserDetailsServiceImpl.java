package com.springcloud.apigateway.security.service.impl;

import com.springcloud.apigateway.security.entity.SecurityUser;
import com.springcloud.apigateway.security.service.SystemUserDetailsService;
import com.springcloud.apigateway.securityuser.systemuser.dao.SystemUserDao;
import com.springcloud.apigateway.securityuser.systemuser.dao.SystemUserRoleDao;
import com.springcloud.apigateway.securityuser.systemuser.entity.SystemUser;
import com.springcloud.apigateway.securityuser.systemuser.entity.SystemUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SystemUserDetailsServiceImpl implements SystemUserDetailsService {

    @Autowired
    private SystemUserDao systemUserDao;

    @Autowired
    private SystemUserRoleDao systemUserRoleDao;

    @Override
    public SecurityUser loadUserBySystemUsername(String username) throws UsernameNotFoundException {
        SystemUser  systemUser = systemUserDao.findSystemUserByUsername(username);

        if (systemUser == null){
            UsernameNotFoundException usernameNotFoundException = new UsernameNotFoundException("用户名不存在");
            usernameNotFoundException.printStackTrace();
            throw usernameNotFoundException;
        }
        SystemUserRole systemUserRole = systemUserRoleDao.findBySystemUserId(systemUser.getId());

        return new SecurityUser(systemUser, systemUserRole);
    }
}
