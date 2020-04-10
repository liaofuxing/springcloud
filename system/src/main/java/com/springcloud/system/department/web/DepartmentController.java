package com.springcloud.system.department.web;

import com.springcloud.common.utils.ResultVOUtils;
import com.springcloud.common.vo.DatePageVO;
import com.springcloud.common.vo.ResultVO;
import com.springcloud.common.vo.SelectFormatVO;
import com.springcloud.system.department.dto.DepartmentDto;
import com.springcloud.system.department.service.DepartmentService;
import com.springcloud.system.department.vo.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/17 16:00
 **/
@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询部门下拉框列表
     *
     * @return ResultVO<List<SelectFormatVO>>
     */
    @GetMapping("/getDepartmentSelect")
    @ResponseBody
    public ResultVO<List<SelectFormatVO>> getDepartmentSelect() {
        List<SelectFormatVO> selectFormatVO = departmentService.findDepartmentAll();
        return ResultVOUtils.success(selectFormatVO);
    }

    /**
     * 查询部门列表 分页
     *
     * @return ResultVO
     */
    @GetMapping("/getDepartmentList")
    @ResponseBody
    public ResultVO<DatePageVO<DepartmentVO>> getDepartmentList(DepartmentDto departmentDto) {
        DatePageVO<DepartmentVO> departmentVOPage = departmentService.findDepartmentPage(departmentDto);
        return ResultVOUtils.success(departmentVOPage);
    }

    /**
     * 新增
     *
     * @param departmentDto dto
     *
     * @return ResultVO
     */
    @PostMapping("/addDepartment")
    @ResponseBody
    public ResultVO<Object> addDepartment(@RequestBody DepartmentDto departmentDto) {
        departmentService.addDepartment(departmentDto);
        return ResultVOUtils.success(null);
    }

    /**
     *
     * 查询部门列表 分页
     *
     * @param departmentDto dto
     *
     * @return ResultVO
     */
    @PostMapping("/editDepartment")
    @ResponseBody
    public ResultVO<Object> editDepartment(@RequestBody DepartmentDto departmentDto) {
        departmentService.editDepartment(departmentDto);
        return ResultVOUtils.success(null);
    }

    // 校验角色名重复
    @GetMapping("/validateDepartmentNameRepeat")
    @ResponseBody
    public ResultVO<Boolean> validateDepartmentNameRepeat(String roleName, Integer id) {
        Boolean validate = departmentService.validateDepartmentNameRepeat(roleName, id);
        return ResultVOUtils.success(validate);
    }
}
