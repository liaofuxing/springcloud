package com.springcloud.system.role.service;


import com.springcloud.common.vo.DatePageVO;
import com.springcloud.common.vo.SelectFormatVO;
import com.springcloud.system.role.dto.RoleInfoDto;
import com.springcloud.system.role.entity.RoleInfo;
import com.springcloud.system.role.vo.RoleInfoVO;

import java.util.List;

public interface RoleInfoService {

    List<SelectFormatVO> findRoleAll();

    RoleInfo findByRoleName(String roleName);

    RoleInfo findRoleInfoByUserId(Integer userId);

    void editRole(RoleInfoDto roleInfoDto);

    void addRole(RoleInfoDto roleInfoDto);

    DatePageVO<RoleInfoVO> findRoleInfoPage(RoleInfoDto roleInfoDto);

    Boolean validateRoleNameRepeat(String roleName, Integer id);
}
