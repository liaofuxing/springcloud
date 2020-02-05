package myspringcloud.commodity.service;

import myspringcloud.commodity.entity.CommodityInfo;

import java.util.List;

public interface CommodityService {
    List<CommodityInfo> findCommodityAll();
}
