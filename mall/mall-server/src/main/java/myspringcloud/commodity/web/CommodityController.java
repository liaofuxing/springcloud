package myspringcloud.commodity.web;


import myspringcloud.entity.SystemUserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import myspringcloud.systemclient.SystemClient;


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
    public SystemUserResult findSystemUser(@RequestParam String id){
        SystemUserResult systemUserResult = systemClient.findSystemUserById(id);
        System.out.println(systemUserResult.getId());
        return systemUserResult;
    }
}
