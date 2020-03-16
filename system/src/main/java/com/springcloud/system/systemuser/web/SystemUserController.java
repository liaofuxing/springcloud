package com.springcloud.system.systemuser.web;


import com.springcloud.common.entity.DatePageVO;
import com.springcloud.common.enums.ExceptionEnums;
import com.springcloud.common.exception.ExceptionUtils;
import com.springcloud.common.utils.ResultVOUtils;
import com.springcloud.common.vo.ResultVO;
import com.springcloud.system.systemuser.dto.SystemUserDto;
import com.springcloud.system.systemuser.entity.SystemUser;
import com.springcloud.system.systemuser.service.SystemUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * 系统用户控制器
 * @author liaofuxing
 * @date 2019/03/10 4:39
 * @E-mail liaofuxing@outlook.com
 */
@Controller
@RequestMapping("/systemUser")
public class SystemUserController {

    @Autowired
    public SystemUserService systemUserService;

    @PostMapping("/findSystemUserList")
    @ResponseBody
    public ResultVO<SystemUser> findSystemUserList(SystemUserDto systemUserDto) {
        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(systemUserDto, systemUser);
        List<SystemUser> systemUserList = systemUserService.findSystemUserList(systemUser);

        return ResultVOUtils.success(systemUserList);
    }

    /**
     * 运营平台用户分页查询
     * @param systemUserDto
     * @return
     */
    @PostMapping("/findSystemUserPage")
    @ResponseBody
    public ResultVO<SystemUser> findSystemUserPage(SystemUserDto systemUserDto) {
        DatePageVO<SystemUser> systemUserPage = systemUserService.findSystemUserPage(systemUserDto);
        return ResultVOUtils.success(systemUserPage);
    }

    @GetMapping("/findSystemUserById")
    @ResponseBody
    public ResultVO<SystemUser> findSystemUserById(@RequestParam Integer id) {
        SystemUser systemUserById = systemUserService.findSystemUserById(id);
        return ResultVOUtils.success(systemUserById);
    }

    /**
     * 测试传一个对象,给API调用
     * @param systemUserInfo
     * @return
     */
    @PostMapping("/api/findSystemUser")
    @ResponseBody
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
    @ResponseBody
    public ResultVO<SystemUser> findSystemUserByIdApi(@RequestParam Integer id) {
        SystemUser systemUserById = systemUserService.findSystemUserById(id);
        SystemUser systemUserInfo = new SystemUser();
        BeanUtils.copyProperties(systemUserById,systemUserInfo);
        return ResultVOUtils.success(systemUserInfo);
    }

}
