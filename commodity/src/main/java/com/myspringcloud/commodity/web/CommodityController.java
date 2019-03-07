package com.myspringcloud.commodity.web;

import com.myspringcloud.commodity.client.SystemClient;
import com.myspringcloud.commodity.entity.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaofuxing
 * @date 2019/03/08 2:40
 */
@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private SystemClient systemClient;

    /**
     * feign的实例方法
     *
     * @param id
     * @return
     */
    @GetMapping("/findSystemUser")
    public SystemUser findShopUser(@RequestParam String id){
        SystemUser shopUserById = systemClient.findSystemUserById(id);
        System.out.println(shopUserById.getId());
        return shopUserById;
    }
}
