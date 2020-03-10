package com.springcloud.system.systemuser.web;


import com.springcloud.common.enums.ExceptionEnums;
import com.springcloud.common.exception.ExceptionUtils;
import com.springcloud.common.utils.ResultVOUtils;
import com.springcloud.common.vo.ResultVO;
import com.springcloud.system.systemuser.entity.SystemUser;
import com.springcloud.system.systemuser.service.SystemUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
     * 测试传一个对象,给API调用
     * @param systemUserInfo
     * @return
     */
    @PostMapping("/api/findSystemUser")
    public ResultVO<SystemUser> findSystemUserById(@RequestBody @Valid SystemUser systemUserInfo, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
            throw new ExceptionUtils(ExceptionEnums.SYSTEM_EXCEPTION);
        }
        SystemUser systemUserById = systemUserService.findSystemUserById(systemUserInfo.getId());
        return ResultVOUtils.success(systemUserById);
    }


    /**
     * 给API调用
     * @param id
     * @return
     */
    @GetMapping("/api/findSystemUserById")
    public ResultVO<SystemUser> findSystemUserByIdApi(@RequestParam String id) {
        SystemUser systemUserById = systemUserService.findSystemUserById(id);
        SystemUser systemUserInfo = new SystemUser();
        BeanUtils.copyProperties(systemUserById,systemUserInfo);
        return ResultVOUtils.success(systemUserInfo);
    }

}
