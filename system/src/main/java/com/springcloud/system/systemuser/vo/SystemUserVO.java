package com.springcloud.system.systemuser.vo;

import com.springcloud.common.entity.PageInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 系统用户(运营平台用户)VO
 *
 * @author liaofuxing
 * @date 2020/03/13 22:01
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class SystemUserVO extends PageInfo {

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

    /**
     * 账户是否被禁用 0.否 1.是
     */
    private Integer enabledFlag;


    /**
     * 以下不是数据库字段
     */
    /**
     * 角色
     */
    private Integer roleId;

    /**
     *  部门
     */
    private Integer departmentId;
}
