package com.springcloud.apigateway.securityuser.systemuser.dao;

import com.springcloud.apigateway.securityuser.systemuser.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author liaofuxing
 * @date 2020/03/13 22:01
 */
@Repository
public interface SystemUserDao extends JpaRepository<SystemUser, Integer> {

    SystemUser findSystemUserByUsername(String username);

}
