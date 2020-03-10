package com.springcloud.mall.commodity.entity;

import lombok.Data;

/**
 * 商城用户
 */
@Data
public class SystemUser {

    /**
     * 商城用户id
     */
    private String id;


    /**
     * 账号
     */
    private String account;

    /**
     * 商城用户用户名
     */
    private String username;

    /**
     * 商城用户密码
     */
    private String password;

}
