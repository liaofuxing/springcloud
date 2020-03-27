package com.springcloud.apigateway.securityuser.systemuser.service.impl;


import com.springcloud.apigateway.securityuser.systemuser.dao.SystemUserRoleDao;
import com.springcloud.apigateway.securityuser.systemuser.entity.SystemUserRole;
import com.springcloud.apigateway.securityuser.systemuser.service.SystemUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/16 21:25
 **/
@Service
public class SystemUserRoleServiceImpl implements SystemUserRoleService {

    @Autowired
    private SystemUserRoleDao systemUserRoleRepository;

    /**
     * 查询列表
     * @return
     */
    @Override
    public List<SystemUserRole> findSystemUserRoleAll() {
        return null;
    }

    /**
     * 根据systemUserId查询关系
     * @param systemUserId
     * @return
     */
    @Override
    public SystemUserRole findSystemUserRoleBySystemUserId(Integer systemUserId) {
        return systemUserRoleRepository.findBySystemUserId(systemUserId);
    }


    /**
     * 新增
     *
     *  //this @Transactional is Propagation.REQUIRES_NEW，无论谁调用这个方法，该方法都会新启动一个事务
     * this @Transactional is Propagation.REQUIRED， 如果调用者事务支持该事务，继承调用者的事务
     * @param systemUserRole
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addSystemUserRole(SystemUserRole systemUserRole) {
        systemUserRoleRepository.save(systemUserRole);
    }
}
