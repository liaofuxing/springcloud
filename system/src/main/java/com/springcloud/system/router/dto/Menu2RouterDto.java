package com.springcloud.system.router.dto;

import lombok.Data;

import javax.persistence.Id;

/**
 * 前端路由实体类
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date: 2019/08/07 11:45
 **/
@Data
public class Menu2RouterDto {

    @Id
    private Integer id;

    private String name;

    private String path;

    private String component;

    private String title;

    private String icon;

    private String redirect;

    private Integer parent;

    private Integer level;

    private String description;


    public Menu2RouterDto() {
    }

}
