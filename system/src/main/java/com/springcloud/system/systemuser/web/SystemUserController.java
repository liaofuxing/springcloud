package com.springcloud.system.systemuser.web;


import com.springcloud.common.vo.DatePageVO;
import com.springcloud.common.enums.ExceptionEnums;
import com.springcloud.common.exception.ExceptionUtils;
import com.springcloud.common.utils.ResultVOUtils;
import com.springcloud.common.vo.ResultVO;
import com.springcloud.system.systemuser.dto.SystemUserDto;
import com.springcloud.system.systemuser.entity.SystemUser;
import com.springcloud.system.systemuser.service.SystemUserService;
import com.springcloud.system.systemuser.vo.SystemUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
 *
 * @author liaofuxing
 * @date 2019/03/10 4:39
 * @E-mail liaofuxing@outlook.com
 */
@Controller
@RequestMapping("/systemUser")
@Api(value = "systemUser")
public class SystemUserController {

    @Autowired
    public SystemUserService systemUserService;

    @PostMapping("/findSystemUserList")
    @ResponseBody
    @ApiOperation(value = "/findSystemUserList", notes = "查询用户列表")
    @ApiImplicitParam(name = "systemUserDto", value = "用户对象Dto", dataType = "String")
    public ResultVO<List<SystemUser>> findSystemUserList(@RequestBody SystemUserDto systemUserDto) {
        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(systemUserDto, systemUser);
        List<SystemUser> systemUserList = systemUserService.findSystemUserList(systemUser);

        return ResultVOUtils.success(systemUserList);
    }

    /**
     * 运营平台用户分页查询
     *
     * @param systemUserDto dto
     * @return ResultVO
     */
    @PostMapping("/findSystemUserPage")
    @ResponseBody
    public ResultVO<DatePageVO<SystemUserVO>> findSystemUserPage(@RequestBody SystemUserDto systemUserDto) {
        DatePageVO<SystemUserVO> systemUserPage = systemUserService.findSystemUserPage(systemUserDto);
        return ResultVOUtils.success(systemUserPage);
    }

    @GetMapping("/findSystemUserById")
    @ResponseBody
    public ResultVO<SystemUser> findSystemUserById(@RequestParam Integer id) {
        SystemUser systemUserById = systemUserService.findSystemUserById(id);
        return ResultVOUtils.success(systemUserById);
    }

    @PostMapping("/editSystemUser")
    @ResponseBody
    public ResultVO<Object> editSystemUser(@RequestBody SystemUserDto systemUserDto) {
        systemUserService.editSystemUser(systemUserDto);
        return ResultVOUtils.success(null);
    }

    @PostMapping("/addSystemUser")
    @ResponseBody
    public ResultVO<Object> addSystemUser(@RequestBody SystemUserDto systemUserDto) {
        systemUserService.addSystemUser(systemUserDto);
        return ResultVOUtils.success(null);
    }

    // 校验用户名重复
    @GetMapping("/validateUsernameRepeat")
    @ResponseBody
    public ResultVO<Boolean> validateUsernameRepeat(String username, Integer id) {
        Boolean validate = systemUserService.validateUsernameRepeat(username, id);
        return ResultVOUtils.success(validate);
    }

    // 在线用户查询
    @GetMapping("/getUserOnline")
    @ResponseBody
    public ResultVO<List<SystemUserVO>> getUserOnline () {
        List<SystemUserVO> SystemUserVOs = systemUserService.userOnline();
        return ResultVOUtils.success(SystemUserVOs);
    }

    // 强制离线
    @GetMapping("/userForceOffline")
    @ResponseBody
    public ResultVO<List<SystemUserVO>> userForceOffline (Integer userId) {
        List<SystemUserVO> SystemUserVOs = systemUserService.forceOffline(userId);
        return ResultVOUtils.success(SystemUserVOs);
    }



    /**
     * 测试传一个对象,给API调用
     *
     * @param systemUserInfo dto
     * @return ResultVO
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
     *
     * @param id
     * @return
     */
    @GetMapping("/api/findSystemUserById")
    @ResponseBody
    public ResultVO<SystemUser> findSystemUserByIdApi(@RequestParam Integer id) {
        SystemUser systemUserById = systemUserService.findSystemUserById(id);
        SystemUser systemUserInfo = new SystemUser();
        BeanUtils.copyProperties(systemUserById, systemUserInfo);
        return ResultVOUtils.success(systemUserInfo);
    }

}
