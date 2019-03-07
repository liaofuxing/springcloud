package com.myspringcloud.system.shopuser.service.impl;

import com.myspringcloud.system.shopuser.dto.ShopUserDto;
import com.myspringcloud.system.shopuser.entity.ShopUser;
import com.myspringcloud.system.shopuser.repository.ShopUserRepository;
import com.myspringcloud.system.shopuser.service.ShopUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 商城用户服务实现
 */

@Service
public class ShopUserServiceImpl implements ShopUserService {

    @Autowired
    private ShopUserRepository shopUserRepository;

    /**
     * 根据id查询ShopUser
     */
    @Override
    public ShopUser findShopUserById(String ShopUserId) {
        return shopUserRepository.findById(ShopUserId).get();
    }

    @Override
    public ShopUser addShopUser(ShopUserDto shopUserDto) {
        ShopUser shopUser = new ShopUser();
        BeanUtils.copyProperties(shopUserDto, shopUser);
        shopUser.setId(UUID.randomUUID().toString());
        return shopUserRepository.save(shopUser);
    }
}
