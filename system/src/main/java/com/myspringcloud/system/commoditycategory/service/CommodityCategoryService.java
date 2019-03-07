package com.myspringcloud.system.commoditycategory.service;

import com.myspringcloud.system.commoditycategory.dto.CommodityCategoryDto;
import com.myspringcloud.system.commoditycategory.entity.CommodityCategory;


/**
 * 商城用户服务接口
 */
public interface CommodityCategoryService {

    /**
     * 根据 CategoryId 查询商品类别
     * @param CategoryId
     * @return
     */
    CommodityCategory findCommodityCategoryById(Integer CategoryId);

    /**
     * 新增商品类别
     * @param commodityCategoryDto
     * @return
     */
    CommodityCategory addCommodityCategory(CommodityCategoryDto commodityCategoryDto);
}
