package com.springcloud.system.department.dao;

import com.springcloud.system.department.entity.Department;
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

    Department findByDepartmentName(String departmentName);

    Department findByDepartmentNameAndIdNot(String departmentName, Integer id);
}
