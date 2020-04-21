package com.springcloud.apigateway.security.service.impl;

import com.springcloud.apigateway.security.entity.SecurityUser;
import com.springcloud.apigateway.security.service.UserSmsDetailsService;
import com.springcloud.apigateway.securityuser.systemuser.dao.SystemUserDao;
import com.springcloud.apigateway.securityuser.systemuser.dao.SystemUserRoleDao;
import com.springcloud.apigateway.securityuser.systemuser.entity.SystemUser;
import com.springcloud.apigateway.securityuser.systemuser.entity.SystemUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 短信登录数据库校验逻辑
 *
 * @author liaofuxing
 * @date 2020/02/18 11:50
 *
 */
@Service
public class UserSmsDetailsServiceImpl implements UserSmsDetailsService {

    @Autowired
    private SystemUserDao systemUserDao;

    @Autowired
    private SystemUserRoleDao systemUserRoleDao;


    @Override
    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = systemUserDao.findSystemUserByPhone(username);

        if(systemUser == null){
            UsernameNotFoundException usernameNotFoundException = new UsernameNotFoundException("用户名不存在");
            usernameNotFoundException.printStackTrace();
            throw usernameNotFoundException;
        }

        SystemUserRole systemUserRole = systemUserRoleDao.findBySystemUserId(systemUser.getId());

        return new SecurityUser(systemUser, systemUserRole);
    }

}
