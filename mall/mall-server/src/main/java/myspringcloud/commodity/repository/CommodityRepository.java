package myspringcloud.commodity.repository;

import myspringcloud.commodity.entity.CommodityInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommodityRepository  extends JpaRepository<CommodityInfo, String> {

}
