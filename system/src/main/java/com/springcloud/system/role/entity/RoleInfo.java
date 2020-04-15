package com.springcloud.system.role.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 角色表
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/16 20:25
 **/
@Entity
@Table(name = "role")
@Data
public class RoleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String roleName;

    private String description;
}
