package com.springcloud.system.department.service;


import com.springcloud.system.department.entity.SystemUserDepartment;
import com.springcloud.system.role.etity.SystemUserRole;

import java.util.List;

public interface SystemUserDepartmentService {
    List<SystemUserDepartment> findSystemUserDepartmentAll();

    SystemUserDepartment findSystemUserDepartmentBySystemUserId(Integer systemUserId);

}
