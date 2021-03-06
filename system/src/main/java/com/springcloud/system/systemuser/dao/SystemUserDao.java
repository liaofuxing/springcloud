package com.springcloud.system.systemuser.dao;

import com.springcloud.system.systemuser.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liaofuxing
 * @date 2020/03/13 22:01
 */
@Repository
public interface SystemUserDao extends JpaRepository<SystemUser, Integer>, JpaSpecificationExecutor<SystemUser> {

    @Query(value = "select user from SystemUser user")
    List<SystemUser> findSystemUserList(SystemUser systemUser);

    SystemUser findByUsername(String username);

    SystemUser findByUsernameAndIdNot(String username, Integer id);

    List<SystemUser> findByIdIn(List<Integer> id);
}
