package com.myspringcloud.system.systemuser.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * 系统用户(运营平台用户)
 */
@Data
@Entity
public class SystemUser {

    /**
     * 系统用户id
     */
    @Id
    private String id;


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

}
