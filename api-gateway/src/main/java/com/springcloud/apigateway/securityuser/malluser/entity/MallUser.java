package com.springcloud.apigateway.securityuser.malluser.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: liaofuxing
 * @E-mail: liaofuxing@outlook.com
 * @date: 2019/10/05 11:18
 **/

@Data
@Entity
@Table(name = "mall_user")
public class MallUser {
    @Id
    private Integer id;

    private String username;

    private String password;

    private Integer gender;

    private Integer age;

    private String phone;

    private String address;

}
