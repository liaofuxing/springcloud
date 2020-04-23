package com.springcloud.apigateway.securityuser.malluser.service;

import com.springcloud.apigateway.securityuser.malluser.entity.MallUser;


public interface MallUserService {

    MallUser findSystemUserByUsername(String username);
}
