package com.springcloud.system.router.entity;

import lombok.Data;

import java.util.List;

@Data
public class Router2TreeVO {

    private Integer id;

    private String label;

    private String treeChecked;

    private List<Router2TreeVO> children;

    public Router2TreeVO() {

    }

    public Router2TreeVO(Integer id, String label) {
        this.id = id;
        this.label = label;
    }
}
