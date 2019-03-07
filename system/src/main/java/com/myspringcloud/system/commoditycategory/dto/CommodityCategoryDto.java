package com.myspringcloud.system.commoditycategory.dto;

import lombok.Data;

/**
 * 商品类别
 */

@Data
public class CommodityCategoryDto {

    /**
     * 类别id
     */
    private Integer id;

    /**
     * 账号
     */
    private String categoryName;

}
