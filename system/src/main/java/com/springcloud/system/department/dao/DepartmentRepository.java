package com.springcloud.system.department.dao;

import com.springcloud.system.department.entity.Department;
import com.springcloud.system.role.etity.RoleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Resource;

/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/16 20:20
 **/
@Resource
public interface DepartmentRepository  extends JpaRepository<Department, Integer> {

}
