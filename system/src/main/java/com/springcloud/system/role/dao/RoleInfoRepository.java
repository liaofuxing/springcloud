package com.springcloud.system.role.dao;

import com.springcloud.system.role.etity.RoleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Resource;

/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/16 20:20
 **/
@Resource
public interface RoleInfoRepository  extends JpaRepository<RoleInfo, Integer> {

}
