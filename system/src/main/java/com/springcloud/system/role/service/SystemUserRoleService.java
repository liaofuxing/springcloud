package com.springcloud.system.role.service;


import com.springcloud.system.role.etity.SystemUserRole;

import java.util.List;

public interface SystemUserRoleService {
    List<SystemUserRoleService> findSystemUserRoleAll();

    SystemUserRole findSystemUserRoleBySystemUserId(Integer systemUserId);

}
