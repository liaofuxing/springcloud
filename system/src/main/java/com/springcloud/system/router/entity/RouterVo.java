package com.springcloud.system.router.entity;

import lombok.Data;

import java.util.List;

/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2019/08/09 15:26
 **/
@Data
public class RouterVo {

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

    private List<RouterVo> children;

    public RouterVo (){

    }

    public RouterVo(Integer id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }
}
