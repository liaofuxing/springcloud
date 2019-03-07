package com.myspringcloud.system.systemuser.repository;

import com.myspringcloud.system.SystemApplicationTests;
import com.myspringcloud.system.systemuser.entity.SystemUser;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SystemUserRepositoryTest extends SystemApplicationTests {

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Test
    public void findAll(){
        List<SystemUser> all = systemUserRepository.findAll();
        System.out.println(all);
        Assert.assertTrue(all.size() > 0);
    }
}