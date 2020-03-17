package com.springcloud.system.role.service;



import com.springcloud.common.entity.DatePageVO;
import com.springcloud.system.role.dto.RoleInfoDto;
import com.springcloud.system.role.etity.RoleInfo;
import com.springcloud.system.role.vo.RoleInfoVO;
import com.springcloud.system.role.vo.SelectFormatVO;
import com.springcloud.system.role.web.RoleInfoController;

import java.util.List;

public interface RoleInfoService {

    List<SelectFormatVO> findRoleAll();

    RoleInfo findRoleInfoByUserId(Integer userId);

    void editRole(RoleInfoDto roleInfoDto);

    void addRole(RoleInfoDto roleInfoDto);

    DatePageVO<RoleInfoVO> findRoleInfoPage(RoleInfoDto roleInfoDto);


}
