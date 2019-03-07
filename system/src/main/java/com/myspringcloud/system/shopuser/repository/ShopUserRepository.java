package com.myspringcloud.system.shopuser.repository;

import com.myspringcloud.system.shopuser.entity.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopUserRepository extends JpaRepository<ShopUser, String> {

}
