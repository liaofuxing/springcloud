package myspringcloud.commodity.service.impl;

import myspringcloud.commodity.entity.CommodityInfo;
import myspringcloud.commodity.repository.CommodityRepository;
import myspringcloud.commodity.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品服务
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    public CommodityRepository commodityRepository;

    @Override
    public List<CommodityInfo> findCommodityAll() {
        return commodityRepository.findAll();
    }
}
