package com.springcloud.mall.client.systemclient;

import com.springcloud.common.utils.ResultVOUtils;
import com.springcloud.common.vo.ResultVO;
import com.springcloud.mall.commodity.entity.SystemUser;
import org.springframework.stereotype.Component;

/**
 * hystrix熔断器生效回调
 */
@Component
public class SystemClientFallback implements SystemClient {
    @Override
    public ResultVO<SystemUser> findSystemUserById(Integer id) {
        SystemUser systemUser = new SystemUser();
        return ResultVOUtils.error(systemUser);
    }

    @Override
    public ResultVO<SystemUser> findSystemUser(SystemUser systemUserInfo) {
        SystemUser systemUser = new SystemUser();
        return ResultVOUtils.error(systemUser);
    }
}
