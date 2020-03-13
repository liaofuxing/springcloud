package com.springcloud.system.systemuser.entity;


import lombok.Data;

import javax.persistence.*;


/**
 * 系统用户(运营平台用户)
 * @author liaofuxing
 * @date 2020/03/13 22:04
 */
@Data
@Entity
public class SystemUser {

    /**
     * 系统用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
