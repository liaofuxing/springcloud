package system.commoditycategory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import system.commoditycategory.entity.CommodityCategory;

public interface CommodityCategoryRepository extends JpaRepository<CommodityCategory, Integer> {

}
