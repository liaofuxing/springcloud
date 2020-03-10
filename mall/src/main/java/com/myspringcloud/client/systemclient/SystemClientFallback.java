package com.myspringcloud.client.systemclient;

import com.myspringcloud.commodity.entity.SystemUser;
import com.myspringcloud.common.utils.ResultVOUtils;
import com.myspringcloud.common.vo.ResultVO;
import org.springframework.stereotype.Component;

@Component
public class SystemClientFallback implements SystemClient {
    @Override
    public ResultVO<SystemUser> findSystemUserById(String id) {
        return ResultVOUtils.error("hystrix 生效");
    }

    @Override
    public ResultVO<SystemUser> findSystemUser(SystemUser systemUserInfo) {
        return ResultVOUtils.error("hystrix 生效");
    }
}
