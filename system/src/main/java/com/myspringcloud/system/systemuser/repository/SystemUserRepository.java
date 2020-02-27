package com.myspringcloud.system.systemuser.repository;

import com.myspringcloud.system.systemuser.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUserRepository extends JpaRepository<SystemUser, String> {

}
