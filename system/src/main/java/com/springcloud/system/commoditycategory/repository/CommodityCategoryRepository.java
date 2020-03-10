package com.springcloud.system.commoditycategory.repository;


import com.springcloud.system.commoditycategory.entity.CommodityCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommodityCategoryRepository extends JpaRepository<CommodityCategory, Integer> {

}
