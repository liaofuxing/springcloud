package com.springcloud.system.role.web;

import com.springcloud.common.entity.DatePageVO;
import com.springcloud.common.utils.ResultVOUtils;
import com.springcloud.common.vo.ResultVO;
import com.springcloud.system.role.dto.RoleInfoDto;
import com.springcloud.system.role.service.RoleInfoService;
import com.springcloud.system.role.vo.RoleInfoVO;
import com.springcloud.system.role.vo.SelectFormatVO;
import com.springcloud.system.router.entity.MenuRole;
import com.springcloud.system.router.service.MenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private MenuRoleService menuRoleService;

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
     * @return ResultVO<DatePageVO<RoleInfoVO>>
     */
    @GetMapping("/getRoleList")
    @ResponseBody
    public ResultVO<DatePageVO<RoleInfoVO>> getRoleList(RoleInfoDto roleInfoDto) {
        DatePageVO<RoleInfoVO> roleInfoPage = roleInfoService.findRoleInfoPage(roleInfoDto);
        return ResultVOUtils.success(roleInfoPage);
    }

    /**
     * 编辑角色
     *
     * @return ResultVO<Object>
     */
    @PostMapping("/editRole")
    @ResponseBody
    public ResultVO<Object> editRole(@RequestBody RoleInfoDto roleInfoDto) {
        roleInfoService.editRole(roleInfoDto);
        return ResultVOUtils.success(null);
    }

    /**
     * 新增角色
     *
     * @return ResultVO<Object>
     */
    @PostMapping("/addRole")
    @ResponseBody
    public ResultVO<Object> addRole(@RequestBody RoleInfoDto roleInfoDto) {
        roleInfoService.addRole(roleInfoDto);
        return ResultVOUtils.success(null);
    }

    /**
     * 获取角色对应菜单
     *
     * @return ResultVO<List<Integer>>
     */
    @GetMapping("/getRoleMenu")
    @ResponseBody
    public ResultVO<List<Integer>> getRoleMenu(RoleInfoDto roleInfoDto) {
        MenuRole menuRole = menuRoleService.findMenuRole(roleInfoDto.getId());
        List<Integer> menu = new ArrayList<>();
        if (menuRole != null){
            String[] menuArr = menuRole.getMenu().split(",");
            for (String s: menuArr) {
                menu.add(Integer.parseInt(s));
            }
        }
        return ResultVOUtils.success(menu);
    }

    // 校验角色名重复
    @GetMapping("/validateRoleNameRepeat")
    @ResponseBody
    public ResultVO<Boolean> validateRoleNameRepeat(String roleName, Integer id) {
        Boolean validate = roleInfoService.validateRoleNameRepeat(roleName, id);
        return ResultVOUtils.success(validate);
    }
}
