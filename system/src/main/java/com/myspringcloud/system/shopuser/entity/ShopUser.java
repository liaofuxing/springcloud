package com.myspringcloud.system.shopuser.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 商城用户
 */

@Data
@Entity
public class ShopUser {

    @Id
    /**
     * 商城用户id
     */
    private String id;

    /**
     * 商城用户用户名
     */
    private String username;

    /**
     * 商城用户密码
     */
    private String password;

}
