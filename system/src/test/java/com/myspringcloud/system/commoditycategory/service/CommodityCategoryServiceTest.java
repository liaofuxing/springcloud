package com.myspringcloud.system.commoditycategory.service;

import com.myspringcloud.system.SystemApplicationTests;
import com.myspringcloud.system.commoditycategory.dto.CommodityCategoryDto;
import com.myspringcloud.system.commoditycategory.entity.CommodityCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 商品类别测试
 */
@Component
public class CommodityCategoryServiceTest extends SystemApplicationTests {

    @Autowired
    private CommodityCategoryService commodityCategoryService;

    /**
     * 根据id查询商品类别
     */
    @Test
    public void findCommodityCategoryById() {
        CommodityCategory commodityCategory = commodityCategoryService.findCommodityCategoryById(Integer.parseInt("1"));
        Assert.assertTrue(commodityCategory.getId() == 1L);
    }

    /**
     * 新增商品类别
     */
    @Test
    public void addCommodityCategory() {
        CommodityCategoryDto commodityCategoryDto = new CommodityCategoryDto();
        commodityCategoryDto.setCategoryName("玩的");
        CommodityCategory commodityCategory = commodityCategoryService.addCommodityCategory(commodityCategoryDto);
        Assert.assertNotNull(commodityCategory.getCategoryName());
    }
}