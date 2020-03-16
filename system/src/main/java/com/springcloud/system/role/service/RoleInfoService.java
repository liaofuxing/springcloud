package com.springcloud.system.role.service;



import com.springcloud.system.role.etity.RoleInfo;
import com.springcloud.system.role.vo.RoleInfoVO;

import java.util.List;

public interface RoleInfoService {

    List<RoleInfoVO> findRoleAll();

    RoleInfo findRoleInfoByRoleId(Integer roleId);
}
