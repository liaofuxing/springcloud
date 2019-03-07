package com.myspringcloud.system.systemuser.service.impl;


import com.myspringcloud.system.SystemApplicationTests;
import com.myspringcloud.system.systemuser.dto.SystemUserDto;
import com.myspringcloud.system.systemuser.entity.SystemUser;
import com.myspringcloud.system.systemuser.service.SystemUserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户服务测试类
 */
@Component
public class SystemUserServiceImplTest extends SystemApplicationTests {

    @Autowired
    private SystemUserService systemUserService;

    /**
     * 根据id查询商城用户
     */
    @Test
    public void findSystemUserById() {
        SystemUser findSystemUserById = systemUserService.findSystemUserById("1");
        Assert.assertTrue("1".equals(findSystemUserById.getId()));
    }

    /**
     * 新增商城用户
     */
    @Test
    public void addSystemUser() {
        SystemUserDto systemUserDto = new SystemUserDto();
        systemUserDto.setUsername("liao");
        systemUserDto.setPassword("131231");
        systemUserDto.setAccount(String.valueOf(System.currentTimeMillis()));
        SystemUser systemUser = systemUserService.addSystemUser(systemUserDto);
        Assert.assertNotNull(systemUser.getId());
    }
}