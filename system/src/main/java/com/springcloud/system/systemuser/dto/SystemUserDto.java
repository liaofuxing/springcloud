package com.springcloud.system.systemuser.dto;

import com.springcloud.common.entity.PageInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 系统用户(运营平台用户)Dto
 *
 * @author liaofuxing
 * @date 2020/03/13 22:01
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class SystemUserDto extends PageInfo {

    /**
     * 系统用户id
     */
    private Integer id;


    /**
     * 账号
     */
    private String account;

    /**
     * 系统用户用户名
     */
    private String username;

    /**
     * 系统用户密码
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 地址
     */
    private String address;

    /**
     * 电话
     */
    private String phone;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 创建时间
     */
    private String creationTime;

    /**
     * 修改时间
     */
    private String updateTime;


    // 以下不是数据库里的字段
    /**
     * 角色
     */
    private Integer roleId;

    /**
     *  部门
     */
    private Integer departmentId;

}
