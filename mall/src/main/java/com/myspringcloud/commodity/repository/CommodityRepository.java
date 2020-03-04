package com.myspringcloud.commodity.repository;

import com.myspringcloud.commodity.entity.CommodityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * jap实现分页
 */
public interface CommodityRepository  extends PagingAndSortingRepository<CommodityInfo, String> {

}
