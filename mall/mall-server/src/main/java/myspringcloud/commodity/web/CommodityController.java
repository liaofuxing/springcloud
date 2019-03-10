package myspringcloud.commodity.web;


import com.myspringcloud.common.enums.ExceptionEnums;
import com.myspringcloud.common.exception.ExceptionUtils;
import com.myspringcloud.common.vo.ResultVO;
import myspringcloud.entity.SystemUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("/findSystemUserById")
    public ResultVO<SystemUserInfo> findSystemUserById(@RequestParam String id){

        ResultVO<SystemUserInfo> resultVO = systemClient.findSystemUserById(id);
        return resultVO;
    }

    /**
     *
     * 这是一个Feign示例方法
     *
     * Feign调用必须是全路径,而不是方法上的路径 (/systemUser/api/findSystemUserById)
     * @param systemUserInfo
     * @return
     */
    @PostMapping("/findSystemUser")
    public ResultVO<SystemUserInfo> findSystemUser(@RequestBody SystemUserInfo systemUserInfo) {
        System.out.println(systemUserInfo);
        try {
            ResultVO<SystemUserInfo> resultVO = systemClient.findSystemUser(systemUserInfo);
            return resultVO;
        } catch (Exception e) {
            throw new ExceptionUtils(ExceptionEnums.SYSTEM_EXCEPTION);
        }
    }
}
