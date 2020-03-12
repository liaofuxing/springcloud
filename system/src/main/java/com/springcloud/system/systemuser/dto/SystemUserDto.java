package com.springcloud.system.systemuser.dto;

import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

/**
 * 商城用户
 */

@Data
public class SystemUserDto {

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
    private String gender;

    /**
     * 所属部门id
     */
    private Integer departmentId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 创建时间
     */
    private String creationTime;

    /**
     * 修改时间
     */
    private String updateTime;

}
