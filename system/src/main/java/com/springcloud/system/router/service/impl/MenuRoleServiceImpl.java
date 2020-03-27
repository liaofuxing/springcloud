package com.springcloud.system.router.service.impl;

import com.springcloud.system.router.dao.MenuRoleDao;
import com.springcloud.system.router.dao.RouterDao;
import com.springcloud.system.router.entity.MenuRole;
import com.springcloud.system.router.entity.Router;
import com.springcloud.system.router.service.MenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2019/08/09 15:21
 **/
@Service
public class MenuRoleServiceImpl implements MenuRoleService {

    @Autowired
    private MenuRoleDao menuRoleDao;
    @Autowired
    private RouterDao routerDao;


    /**
     *
     *
     * @return
     */
    public List<Integer> findMenuRoleList(Integer roleId) {
        MenuRole menuRole = menuRoleDao.findByRoleId(roleId);
        List<Integer> menu = new ArrayList<>();
        if (menuRole != null){
            String[] menuArr = menuRole.getMenu().split(",");
            for (String s: menuArr) {
                Optional<Router> byId = routerDao.findById(Integer.parseInt(s));
                if(byId.get().getParent() != 0) {
                    menu.add(Integer.parseInt(s));
                }
            }
        }
        return menu;
    }


    /**
     *
     *
     * @return
     */
    public MenuRole findMenuRole(Integer roleId) {
        return menuRoleDao.findByRoleId(roleId);
    }

    /**
     *
     *
     * @return
     */
    @Transactional
    public void addMenuRole(MenuRole menuRole) {
         menuRoleDao.save(menuRole);
    }

}
