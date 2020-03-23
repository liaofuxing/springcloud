package com.springcloud.apigateway.securityuser.systemuser.service;

import com.springcloud.apigateway.securityuser.systemuser.entity.SystemUser;

public interface SystemUserService {

    SystemUser findSystemUserByUsername(String username);
}
