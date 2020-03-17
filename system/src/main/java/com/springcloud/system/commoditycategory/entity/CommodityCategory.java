package com.springcloud.system.commoditycategory.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 商品类别
 */

@Data
@Entity
public class CommodityCategory {


    /**
     * 类别id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 账号
     */
    private String categoryName;

}
