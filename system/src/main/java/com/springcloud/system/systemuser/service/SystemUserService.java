package com.springcloud.system.systemuser.service;


import com.springcloud.system.systemuser.dto.SystemUserDto;
import com.springcloud.system.systemuser.entity.SystemUser;

/**
 * 系统用户（运营平台）服务接口
 */
public interface SystemUserService {

    /**
     * 根据 ShopUserId 查询用户
     * @param ShopUserId
     * @return
     */
    SystemUser findSystemUserById(String ShopUserId);

    /**
     * 新增商城用户
     * @param systemUserDto
     * @return
     */
    SystemUser addSystemUser(SystemUserDto systemUserDto);
}
