
package com.springcloud.system.role.dao;

import com.springcloud.system.role.entity.SystemUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Resource;

/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/16 20:25
 **/
@Resource
public interface SystemUserRoleDao extends JpaRepository<SystemUserRole, Integer> {

    SystemUserRole findBySystemUserId(Integer systemUserId);

}
