package com.springcloud.system.router.dao;

import com.springcloud.system.router.entity.MenuRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRoleDao extends JpaRepository<MenuRole, Integer> {

    MenuRole findByRoleId(Integer roleId);
}
