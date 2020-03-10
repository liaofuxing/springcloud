package com.springcloud.system.systemuser.service.impl;

import com.springcloud.system.systemuser.dto.SystemUserDto;
import com.springcloud.system.systemuser.entity.SystemUser;
import com.springcloud.system.systemuser.repository.SystemUserRepository;
import com.springcloud.system.systemuser.service.SystemUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 商城用户服务实现
 */

@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserRepository systemUserRepository;

    /**
     * 根据id查询ShopUser
     */
    @Override
    public SystemUser findSystemUserById(String ShopUserId) {
        return systemUserRepository.findById(ShopUserId).get();
    }

    @Override
    public SystemUser addSystemUser(SystemUserDto systemUserDto) {
        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(systemUserDto, systemUser);
        systemUser.setId(UUID.randomUUID().toString());
        return systemUserRepository.save(systemUser);
    }
}
