package com.myspringcloud.commodity.service;

import com.myspringcloud.commodity.entity.CommodityInfo;

import java.util.List;

public interface CommodityService {
    List<CommodityInfo> findCommodityAll();
}
