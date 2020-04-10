package com.springcloud.system.systemuser.service;


import com.springcloud.common.vo.DatePageVO;
import com.springcloud.system.systemuser.dto.SystemUserDto;
import com.springcloud.system.systemuser.entity.SystemUser;
import com.springcloud.system.systemuser.vo.SystemUserVO;

import java.util.List;

/**
 * 系统用户（运营平台）Service 接口
 *
 * @author liaofuxing
 * @date 2020/03/13 20:01
 */
public interface SystemUserService {

    /**
     * 根据 id 查询用户
     *
     * @param id id
     *
     * @return SystemUser
     */
    SystemUser findSystemUserById(Integer id);

    /**
     * 根据 username 查询用户
     *
     * @param username username
     *
     * @return SystemUser
     */
    SystemUser findSystemUserByUsername(String username);

    /**
     * 查询用户列表
     *
     * @param systemUser
     *
     * @return
     */
    List<SystemUser> findSystemUserList(SystemUser systemUser);

    /**
     * 分页查询
     *
     * @param systemUserDto
     * @return
     */
    DatePageVO<SystemUserVO> findSystemUserPage(SystemUserDto systemUserDto);

    /**
     * 新增运营平台用户
     *
     * @param systemUserDto
     *
     */
    void addSystemUser(SystemUserDto systemUserDto);


    /**
     * 修改运营平台用户信息
     *
     * @param systemUserDto
     * @return
     */
    void editSystemUser(SystemUserDto systemUserDto);

    Boolean validateUsernameRepeat(String username,Integer id);

    List<SystemUserVO>  userOnline();

    List<SystemUserVO> forceOffline(Integer userId);
}
