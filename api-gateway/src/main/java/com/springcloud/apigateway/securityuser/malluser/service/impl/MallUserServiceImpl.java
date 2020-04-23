package com.springcloud.apigateway.securityuser.malluser.service.impl;

import com.springcloud.apigateway.securityuser.malluser.dao.MallUserDao;
import com.springcloud.apigateway.securityuser.malluser.entity.MallUser;
import com.springcloud.apigateway.securityuser.malluser.service.MallUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liaofuxing
 * @E-mail  liaofuxing@outlook.com
 * @date 2019/10/05 12:05
 **/
@Service
public class MallUserServiceImpl implements MallUserService {

    @Autowired
    private MallUserDao mallUserDao;

    @Override
    public MallUser findSystemUserByUsername(String username) {
        return mallUserDao.findMallUserByUsername(username);
    }
}
