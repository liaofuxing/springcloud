package com.springcloud.apigateway.securityuser.systemuser.service;


import com.springcloud.apigateway.securityuser.systemuser.entity.SystemUserRole;

import java.util.List;

public interface SystemUserRoleService {
    List<SystemUserRole> findSystemUserRoleAll();

    SystemUserRole findSystemUserRoleBySystemUserId(Integer systemUserId);

    void addSystemUserRole(SystemUserRole systemUserRole);

}
