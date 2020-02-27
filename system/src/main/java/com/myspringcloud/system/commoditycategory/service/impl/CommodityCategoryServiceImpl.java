package com.myspringcloud.system.commoditycategory.service.impl;

import com.myspringcloud.system.commoditycategory.dto.CommodityCategoryDto;
import com.myspringcloud.system.commoditycategory.entity.CommodityCategory;
import com.myspringcloud.system.commoditycategory.repository.CommodityCategoryRepository;
import com.myspringcloud.system.commoditycategory.service.CommodityCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 商品类别
 */

@Service
public class CommodityCategoryServiceImpl implements CommodityCategoryService {

    @Autowired
    private CommodityCategoryRepository commodityCategoryRepository;

    /**
     * 根据id查询商品类别
     */
    @Override
    public CommodityCategory findCommodityCategoryById(Integer categoryId) {
        return commodityCategoryRepository.findById(categoryId).get();
    }

    /**
     * 新增商品类别
     * @param commodityCategoryDto
     * @return
     */
    @Override
    public CommodityCategory addCommodityCategory(CommodityCategoryDto commodityCategoryDto) {
        CommodityCategory commodityCategory = new CommodityCategory();
        BeanUtils.copyProperties(commodityCategoryDto, commodityCategory);
        return commodityCategoryRepository.save(commodityCategory);
    }
}
