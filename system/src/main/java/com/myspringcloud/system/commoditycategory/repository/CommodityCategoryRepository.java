package com.myspringcloud.system.commoditycategory.repository;


import com.myspringcloud.system.commoditycategory.entity.CommodityCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommodityCategoryRepository extends JpaRepository<CommodityCategory, Integer> {

}
