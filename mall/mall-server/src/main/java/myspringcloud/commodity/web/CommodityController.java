package myspringcloud.commodity.web;


import com.myspringcloud.common.utils.ResultVOUtils;
import com.myspringcloud.common.vo.ResultVO;
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
    public ResultVO<SystemUserResult> findSystemUser(@RequestParam String id){

        ResultVO<SystemUserResult> resultVO = systemClient.findSystemUserById(id);
        return resultVO;
    }

    @GetMapping("/list")
    public String list(){
        return "ok";
    }
}
