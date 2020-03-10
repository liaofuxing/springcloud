package com.springcloud.mall.commodity.service;


import com.springcloud.mall.commodity.entity.CommodityInfo;

import java.util.List;

public interface CommodityService {
    List<CommodityInfo> findCommodityAll();
}
