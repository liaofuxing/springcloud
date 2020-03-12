package com.springcloud.system.systemuser.service.impl;

import com.springcloud.system.systemuser.dto.SystemUserDto;
import com.springcloud.system.systemuser.entity.SystemUser;
import com.springcloud.system.systemuser.repository.SystemUserRepository;
import com.springcloud.system.systemuser.service.SystemUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 系统用户（运营平台）服务实现
 */

@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserRepository systemUserRepository;

    /**
     * 根据id查询SystemUser
     */
    @Override
    public SystemUser findSystemUserById(Integer systemUserId) {
        return systemUserRepository.findById(systemUserId).get();
    }

    /**
     * 查询用户列表
     * @param systemUser
     * @return
     */
    @Override
    public List<SystemUser> findSystemUserList(SystemUser systemUser) {
        return systemUserRepository.findSystemUserList(systemUser);
    }

    /**
     * 新增用户
     * @param systemUserDto
     * @return
     */
    @Override
    public SystemUser addSystemUser(SystemUserDto systemUserDto) {
        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(systemUserDto, systemUser);
        return systemUserRepository.save(systemUser);
    }
}
