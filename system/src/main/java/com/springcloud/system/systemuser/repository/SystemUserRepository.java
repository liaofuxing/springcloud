package com.springcloud.system.systemuser.repository;

import com.springcloud.system.systemuser.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemUserRepository extends JpaRepository<SystemUser, Integer> {

    @Query(value = "select user from SystemUser user")
    List findSystemUserList(SystemUser systemUser);
}
