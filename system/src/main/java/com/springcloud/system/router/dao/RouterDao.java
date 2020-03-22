package com.springcloud.system.router.dao;

import com.springcloud.system.router.entity.Router;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/19 02:37
 *
 **/
@Repository
public interface RouterDao extends JpaRepository<Router, Integer> {

    List<Router> findByParent(Integer parent);

    Router findByName(String routerName);

    Router findByTitle(String routerTitle);
}
