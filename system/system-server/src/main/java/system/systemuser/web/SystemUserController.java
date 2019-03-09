package system.systemuser.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import system.systemuser.entity.SystemUser;
import system.systemuser.service.SystemUserService;

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

    /**
     * 给API调用
     * @param id
     * @return
     */
    @GetMapping("/api/findSystemUserById")
    public SystemUser findSystemUserByIdApi(@RequestParam String id) {
        SystemUser systemUserById = systemUserService.findSystemUserById(id);
        return systemUserById;
    }

}
