package com.springcloud.apigateway.securityuser.dao;

import com.springcloud.apigateway.securityuser.entity.MallUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: liaofuxing
 * @E-mail: liaofuxing@outlook.com
 * @date: 2019/10/05 12:00
 **/
@Repository
public interface MallUserDao extends JpaRepository<MallUser, Integer> {

    MallUser findSystemUserByUsername(String username);

    MallUser findSystemUserByPhone(String phone);
}
