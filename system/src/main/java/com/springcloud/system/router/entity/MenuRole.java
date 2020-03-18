package com.springcloud.system.router.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class MenuRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer roleId;

    /**
     * 菜单json串
     */
    private String menu;
}
