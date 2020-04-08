package com.springcloud.system.router.vo;

import lombok.Data;

import java.util.List;

/**
 * 返回给前端的路由vo
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2019/08/09 15:26
 **/
@Data
public class RouterVO {

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

    private Integer hidden = 1;

    private List<RouterVO> children;

    public RouterVO() {

    }

    public RouterVO(Integer id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }
}
