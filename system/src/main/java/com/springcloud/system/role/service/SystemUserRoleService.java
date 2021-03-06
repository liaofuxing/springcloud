package com.springcloud.system.role.service;


import com.springcloud.system.role.entity.SystemUserRole;

import java.util.List;

public interface SystemUserRoleService {
    List<SystemUserRole> findSystemUserRoleAll();

    SystemUserRole findSystemUserRoleBySystemUserId(Integer systemUserId);

    void addSystemUserRole(SystemUserRole systemUserRole);

}
