package com.springcloud.common.entity;

import lombok.Data;

/**
 *
 * 分页实体类
 *
 * 每一个需要接收前端页面分页数据的Dto和需要返回分页给前端的VO都应该继承个类
 *
 * @author liaofuxing
 * @date 2020/03/13 22:03
 */
@Data
public class PageInfo {

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 一页多少条
     */
    private Integer pageSize;

}
