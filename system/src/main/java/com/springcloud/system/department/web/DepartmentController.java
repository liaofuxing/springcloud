package com.springcloud.system.department.web;

import com.springcloud.common.utils.ResultVOUtils;
import com.springcloud.common.vo.ResultVO;
import com.springcloud.system.department.service.DepartmentService;
import com.springcloud.system.role.vo.SelectFormatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/02/15 14:18
 **/
@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询部门下拉框列表
     * @return
     */
    @GetMapping("/getDepartmentSelect")
    @ResponseBody
    public ResultVO<List<SelectFormatVO>> getDepartmentSelect(){
        List<SelectFormatVO> selectFormatVO = departmentService.findDepartmentAll();
        return ResultVOUtils.success(selectFormatVO);
    }

    /**
     * 查询部门列表
     * @return
     */
    @GetMapping("/getDepartmentList")
    @ResponseBody
    public ResultVO<List<SelectFormatVO>> getDepartmentList(){
        List<SelectFormatVO> selectFormatVO = departmentService.findDepartmentAll();
        return ResultVOUtils.success(selectFormatVO);
    }
}
