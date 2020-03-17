
package com.springcloud.system.department.dao;

import com.springcloud.system.department.entity.SystemUserDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Resource;

/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/17 15:41
 **/
@Resource
public interface SystemUserDepartmentRepository extends JpaRepository<SystemUserDepartment, Integer> {

    SystemUserDepartment findBySystemUserId(Integer systemUserId);
}
