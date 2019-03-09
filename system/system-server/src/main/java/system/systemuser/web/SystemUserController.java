package system.systemuser.web;

import com.myspringcloud.common.utils.ResultVOUtils;
import com.myspringcloud.common.vo.ResultVO;
import myspringcloud.entity.SystemUserResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import system.systemuser.entity.SystemUser;
import system.systemuser.service.SystemUserService;


/**
 * 系统用户控制器
 * @author liaofuxing
 * @date 2019/03/10 4:39
 * @E-mail liaofuxing@outlook.com
 */
@RestController
@RequestMapping("/systemUser")
public class SystemUserController {

    @Autowired
    public SystemUserService systemUserService;

    @GetMapping("/findSystemUserById")
    public ResultVO<SystemUser> findSystemUserById(@RequestParam String id) {
        SystemUser systemUserById = systemUserService.findSystemUserById(id);
        return ResultVOUtils.success(systemUserById);
    }

    /**
     * 给API调用
     * @param id
     * @return
     */
    @GetMapping("/api/findSystemUserById")
    public ResultVO<SystemUserResult> findSystemUserByIdApi(@RequestParam String id) {
        SystemUser systemUserById = systemUserService.findSystemUserById(id);
        SystemUserResult systemUserResult = new SystemUserResult();
        BeanUtils.copyProperties(systemUserById,systemUserResult);
        return ResultVOUtils.success(systemUserResult);
    }

}
