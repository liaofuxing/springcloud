package com.springcloud.system.role.service.impl;


import com.springcloud.system.role.dao.SystemUserRoleRepository;
import com.springcloud.system.role.etity.SystemUserRole;
import com.springcloud.system.role.service.SystemUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/16 21:25
 *
 **/
@Service
public class SystemUserRoleServiceImpl implements SystemUserRoleService {

    @Autowired
    private SystemUserRoleRepository systemUserRoleRepository;


    @Override
    public List<SystemUserRoleService> findSystemUserRoleAll() {
        return null;
    }

    @Override
    public SystemUserRole findSystemUserRoleBySystemUserId(Integer systemUserId) {
        return systemUserRoleRepository.findBySystemUserId(systemUserId);
    }
}
