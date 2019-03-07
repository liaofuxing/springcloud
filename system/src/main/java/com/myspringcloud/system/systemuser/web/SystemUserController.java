package com.myspringcloud.system.systemuser.web;

import com.myspringcloud.system.systemuser.entity.SystemUser;
import com.myspringcloud.system.systemuser.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商城用户控制器
 */
@RestController
@RequestMapping("/systemUser")
public class SystemUserController {

    @Autowired
    public SystemUserService systemUserService;

    @GetMapping("/findSystemUserById")
    public SystemUser findSystemUserById(@RequestParam String id) {
        SystemUser systemUserById = systemUserService.findSystemUserById(id);
        return systemUserById;
    }

}
