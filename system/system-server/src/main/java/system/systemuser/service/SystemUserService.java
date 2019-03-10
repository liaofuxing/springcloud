package system.systemuser.service;

import system.systemuser.dto.SystemUserDto;
import system.systemuser.entity.SystemUser;


/**
 * 商城用户服务接口
 */
public interface SystemUserService {

    /**
     * 根据 ShopUserId 查询用户
     * @param ShopUserId
     * @return
     */
    SystemUser findSystemUserById(String ShopUserId);

    /**
     * 新增商城用户
     * @param systemUserDto
     * @return
     */
    SystemUser addSystemUser(SystemUserDto systemUserDto);
}