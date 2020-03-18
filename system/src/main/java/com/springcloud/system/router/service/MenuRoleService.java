package com.springcloud.system.router.service;

import com.springcloud.system.router.dao.MenuRoleDao;
import com.springcloud.system.router.dao.RouterDao;
import com.springcloud.system.router.entity.MenuRole;
import com.springcloud.system.router.entity.Router;
import com.springcloud.system.router.entity.Router2TreeVO;
import com.springcloud.system.router.entity.RouterVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2019/08/09 15:21
 **/
@Service
public class MenuRoleService {

    @Autowired
    private MenuRoleDao menuRoleDao;

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
