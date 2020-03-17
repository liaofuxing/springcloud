package com.springcloud.system.department.service.impl;


import com.springcloud.system.department.dao.SystemUserDepartmentRepository;
import com.springcloud.system.department.entity.SystemUserDepartment;
import com.springcloud.system.department.service.SystemUserDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/16 21:25
 *
 **/
@Service
public class SystemUserDepartmentServiceImpl implements SystemUserDepartmentService {

    @Autowired
    private SystemUserDepartmentRepository systemUserDepartmentRepository;


    @Override
    public List<SystemUserDepartment> findSystemUserDepartmentAll() {
        return null;
    }

    @Override
    public SystemUserDepartment findSystemUserDepartmentBySystemUserId(Integer systemUserId) {
        return systemUserDepartmentRepository.findBySystemUserId(systemUserId);
    }
}
