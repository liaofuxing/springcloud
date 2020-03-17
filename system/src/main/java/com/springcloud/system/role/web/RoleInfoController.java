package com.springcloud.system.role.web;

import com.springcloud.common.entity.DatePageVO;
import com.springcloud.common.utils.ResultVOUtils;
import com.springcloud.common.vo.ResultVO;
import com.springcloud.system.role.dto.RoleInfoDto;
import com.springcloud.system.role.etity.RoleInfo;
import com.springcloud.system.role.service.RoleInfoService;
import com.springcloud.system.role.vo.RoleInfoVO;
import com.springcloud.system.role.vo.SelectFormatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/02/15 14:18
 **/
@Controller
@RequestMapping("/role")
public class RoleInfoController {

    @Autowired
    private RoleInfoService roleInfoService;

    /**
     * 查询角色列表
     *
     * @return
     */
    @GetMapping("/getRoleSelect")
    @ResponseBody
    public ResultVO<List<SelectFormatVO>> getRoleSelect() {
        List<SelectFormatVO> roleInfoVO = roleInfoService.findRoleAll();
        return ResultVOUtils.success(roleInfoVO);
    }

    /**
     * 查询角色列表
     *
     * @return
     */
    @GetMapping("/getRoleList")
    @ResponseBody
    public ResultVO<List<SelectFormatVO>> getRoleList(RoleInfoDto roleInfoDto) {
        DatePageVO<RoleInfoVO> roleInfoPage = roleInfoService.findRoleInfoPage(roleInfoDto);
        return ResultVOUtils.success(roleInfoPage);
    }

    /**
     * 编辑角色
     *
     * @return
     */
    @PostMapping("/editRole")
    @ResponseBody
    public ResultVO<List<SelectFormatVO>> editRole(RoleInfoDto roleInfoDto) {
        roleInfoService.editRole(roleInfoDto);
        return ResultVOUtils.success(null);
    }

    /**
     * 编辑角色
     *
     * @return
     */
    @PostMapping("/addRole")
    @ResponseBody
    public ResultVO<List<SelectFormatVO>> addRole(RoleInfoDto roleInfoDto) {
        roleInfoService.addRole(roleInfoDto);
        return ResultVOUtils.success(null);
    }
}
