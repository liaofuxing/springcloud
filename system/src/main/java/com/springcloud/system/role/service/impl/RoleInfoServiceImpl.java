package com.springcloud.system.role.service.impl;



import com.springcloud.system.role.dao.RoleInfoRepository;
import com.springcloud.system.role.etity.RoleInfo;
import com.springcloud.system.role.etity.SystemUserRole;
import com.springcloud.system.role.service.RoleInfoService;
import com.springcloud.system.role.service.SystemUserRoleService;
import com.springcloud.system.role.vo.RoleInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 角色 Service
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/16 20:25
 *
 **/
@Service
public class RoleInfoServiceImpl implements RoleInfoService {

    @Autowired
    private SystemUserRoleService systemUserRoleService;

    @Autowired
    private RoleInfoRepository roleInfoRepository;

    /**
     * 查询所有角色
     * @return
     */
    @Override
    public List<RoleInfoVO> findRoleAll() {
        List<RoleInfoVO> roleInfoVOList = new ArrayList();
        List<RoleInfo> roleInfoList = roleInfoRepository.findAll();
        for (RoleInfo roleInfo: roleInfoList) {
            RoleInfoVO roleInfoVO = new RoleInfoVO(roleInfo.getId(), roleInfo.getRoleName());
            roleInfoVOList.add(roleInfoVO);
        }
        return roleInfoVOList;
    }

    /**
     * 根据userId 查询角色
     * @param userId
     * @return
     */
    @Override
    public RoleInfo findRoleInfoByRoleId(Integer userId) {
        SystemUserRole systemUserRoleBySystemUserId = systemUserRoleService.findSystemUserRoleBySystemUserId(userId);
        Optional<RoleInfo> byId = roleInfoRepository.findById(systemUserRoleBySystemUserId.getRoleId());
        return byId.get();
    }
}
