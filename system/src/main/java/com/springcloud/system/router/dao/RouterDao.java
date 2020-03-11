package com.springcloud.system.router.dao;

import com.springcloud.system.router.entity.Router;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2019/08/09 15:19
 **/
@Repository
public interface RouterDao extends JpaRepository<Router, Integer> {

    List<Router> findByParent(Integer parent);
}
