package com.springcloud.system.systemuser.repository;

import com.springcloud.system.systemuser.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUserRepository extends JpaRepository<SystemUser, String> {

}
