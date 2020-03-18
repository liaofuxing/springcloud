package com.springcloud.system.department.service.impl;


import com.springcloud.system.department.dao.SystemUserDepartmentRepository;
import com.springcloud.system.department.entity.SystemUserDepartment;
import com.springcloud.system.department.service.SystemUserDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/17 16:00
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

    /**
     * 新增或修改用户和部门关系表
     *  //this @Transactional is Propagation.REQUIRES_NEW，无论谁调用这个方法，该方法都会新启动一个事务
     * this @Transactional is Propagation.REQUIRED， 如果调用者事务支持该事务，继承调用者的事务
     * @param systemUserDepartment
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addSystemUserDepartment(SystemUserDepartment systemUserDepartment) {
        systemUserDepartmentRepository.save(systemUserDepartment);
    }
}
