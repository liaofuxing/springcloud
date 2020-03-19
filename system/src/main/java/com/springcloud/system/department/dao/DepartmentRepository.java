package com.springcloud.system.department.dao;

import com.springcloud.system.department.entity.Department;
import com.springcloud.system.role.etity.RoleInfo;
import com.springcloud.system.systemuser.entity.SystemUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.annotation.Resource;

/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/17 15:41
 **/
@Resource
public interface DepartmentRepository extends JpaRepository<Department, Integer>, JpaSpecificationExecutor<Department> {

}
