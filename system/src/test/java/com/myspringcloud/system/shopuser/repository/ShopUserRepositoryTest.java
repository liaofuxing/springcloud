package com.myspringcloud.system.shopuser.repository;

import com.myspringcloud.system.SystemApplicationTests;
import com.myspringcloud.system.shopuser.entity.ShopUser;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ShopUserRepositoryTest extends SystemApplicationTests {

    @Autowired
    private ShopUserRepository shopUserRepository;

    @Test
    public void findAll(){
        List<ShopUser> all = shopUserRepository.findAll();
        System.out.println(all);
        Assert.assertTrue(all.size() > 0);
    }
}