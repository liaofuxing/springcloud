package com.myspringcloud.commodity.repository;

import com.myspringcloud.commodity.entity.CommodityInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommodityRepository  extends JpaRepository<CommodityInfo, String> {

}
