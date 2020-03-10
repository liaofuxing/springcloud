package com.springcloud.mall.commodity.repository;

import com.springcloud.mall.commodity.entity.CommodityInfo;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * jap实现分页
 */
public interface CommodityRepository  extends PagingAndSortingRepository<CommodityInfo, String> {

}
