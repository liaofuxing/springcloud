package com.myspringcloud.system.shopuser.service;

import com.myspringcloud.system.shopuser.dto.ShopUserDto;
import com.myspringcloud.system.shopuser.entity.ShopUser;


/**
 * 商城用户服务接口
 */
public interface ShopUserService {

    /**
     * 根据 ShopUserId 查询用户
     * @param ShopUserId
     * @return
     */
    ShopUser findShopUserById(String ShopUserId);

    /**
     * 新增商城用户
     * @param shopUserDto
     * @return
     */
    ShopUser addShopUser(ShopUserDto shopUserDto);
}
