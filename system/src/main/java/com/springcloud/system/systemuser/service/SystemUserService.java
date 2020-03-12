package com.springcloud.system.systemuser.service;


import com.springcloud.system.systemuser.dto.SystemUserDto;
import com.springcloud.system.systemuser.entity.SystemUser;

import java.util.List;

/**
 * 系统用户（运营平台）服务接口
 */
public interface SystemUserService {

    /**
     * 根据 ShopUserId 查询用户
     * @param systemUserId
     * @return
     */
    SystemUser findSystemUserById(Integer systemUserId);

    /**
     * 查询用户列表
     * @param systemUser
     * @return
     */
    List<SystemUser> findSystemUserList(SystemUser systemUser);

    /**
     * 新增商城用户
     * @param systemUserDto
     * @return
     */
    SystemUser addSystemUser(SystemUserDto systemUserDto);
}
