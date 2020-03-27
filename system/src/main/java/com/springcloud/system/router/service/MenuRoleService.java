package com.springcloud.system.router.service;

import com.springcloud.system.router.entity.MenuRole;

import java.util.List;

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
    List<Integer> findMenuRoleList(Integer roleId);

    /**
     *
     *
     * @return
     */
    void addMenuRole(MenuRole menuRole);

}
