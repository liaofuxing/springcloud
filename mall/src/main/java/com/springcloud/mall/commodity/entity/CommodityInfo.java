package com.springcloud.mall.commodity.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 商品实体类
 */
@Data
@Entity
public class CommodityInfo {

    @Id
    private Integer id;

    /**
     * 商品名称
     */
    private String commodityName;

    /**
     * 单价
     */
    private String price;

    /**
     * 库存
     */
    private String stock;

    /**
     * 单位
     */
    private String unit;

    /**
     * 商品类别id
     */
    private Integer commodityCategoryId;

    /**
     * 商品类别名称
     */
    private String commodityCategoryName;
}
