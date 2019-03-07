package com.myspringcloud.system.shopuser.service.impl;


import com.myspringcloud.system.SystemApplicationTests;
import com.myspringcloud.system.shopuser.dto.ShopUserDto;
import com.myspringcloud.system.shopuser.entity.ShopUser;
import com.myspringcloud.system.shopuser.service.ShopUserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户服务测试类
 */
@Component
public class ShopUserServiceImplTest extends SystemApplicationTests {

    @Autowired
    private ShopUserService shopUserService;

    /**
     * 根据id查询商城用户
     */
    @Test
    public void findShopUserById() {
        ShopUser findShopUserById = shopUserService.findShopUserById("1");
        Assert.assertTrue("1".equals(findShopUserById.getId()));
    }

    /**
     * 新增商城用户
     */
    @Test
    public void addShopUser() {
        ShopUserDto shopUserDto = new ShopUserDto();
        shopUserDto.setUsername("liao");
        shopUserDto.setPassword("131231");
        shopUserDto.setAccount(String.valueOf(System.currentTimeMillis()));
        ShopUser shopUser = shopUserService.addShopUser(shopUserDto);
        Assert.assertNotNull(shopUser.getId());
    }
}