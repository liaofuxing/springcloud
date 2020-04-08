package com.springcloud.system.router.vo;

import lombok.Data;

import java.util.List;

@Data
public class Router2TreeVO {

    private Integer id;

    private String label;

    private Integer parent;

    private List<Router2TreeVO> children;

    public Router2TreeVO() {

    }

    public Router2TreeVO(Integer id, String label, Integer parent) {
        this.id = id;
        this.label = label;
        this.parent = parent;
    }
}
