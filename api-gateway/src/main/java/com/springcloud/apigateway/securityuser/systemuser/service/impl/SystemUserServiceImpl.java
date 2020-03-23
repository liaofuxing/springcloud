package com.springcloud.apigateway.securityuser.systemuser.service.impl;

import com.springcloud.apigateway.securityuser.systemuser.dao.SystemUserDao;
import com.springcloud.apigateway.securityuser.systemuser.entity.SystemUser;
import com.springcloud.apigateway.securityuser.systemuser.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserDao systemUserDao;

    @Override
    public SystemUser findSystemUserByUsername(String username) {
        return systemUserDao.findSystemUserByUsername(username);
    }
}
