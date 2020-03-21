package com.springcloud.system.router.service;

import com.springcloud.system.router.entity.MenuRole;

/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2019/08/09 15:21
 **/
public interface MenuRoleService {


    /**
     *
     *
     * @return
     */
    MenuRole findMenuRole(Integer roleId);

    /**
     *
     *
     * @return
     */
    void addMenuRole(MenuRole menuRole);

}
