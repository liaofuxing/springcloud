package com.springcloud.apigateway.securityuser.service;

import com.springcloud.apigateway.securityuser.dao.MallUserDao;
import com.springcloud.apigateway.securityuser.entity.MallUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: liaofuxing
 * @E-mail: liaofuxing@outlook.com
 * @date: 2019/10/05 12:05
 **/
@Service
public class MallUserService {

    @Autowired
    private MallUserDao mallUserDao;

    public List<MallUser> getSystemUserAll() {
        return mallUserDao.findAll();
    }
}
