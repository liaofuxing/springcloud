package com.springcloud.system.role.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 运营平台用户——角色中间表
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/16 20:25
 **/
@Entity
@Table
@Data
public class SystemUserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer systemUserId;

    private Integer roleId;

}
