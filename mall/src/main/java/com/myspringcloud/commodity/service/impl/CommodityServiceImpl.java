package com.myspringcloud.commodity.service.impl;

import com.myspringcloud.commodity.entity.CommodityInfo;
import com.myspringcloud.commodity.repository.CommodityRepository;
import com.myspringcloud.commodity.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品服务
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    public CommodityRepository commodityRepository;

    /**
     * 商品列表分页查询
     * @return
     */
    @Override
    public List<CommodityInfo> findCommodityAll() {
        Pageable pageable = PageRequest.of(1,10, Sort.by("id"));

        Page<CommodityInfo> commodityInfoPage = commodityRepository.findAll(pageable);
        return commodityInfoPage.getContent();
    }
}
