package com.springcloud.system.department.service;


import com.springcloud.common.vo.DatePageVO;
import com.springcloud.common.vo.SelectFormatVO;
import com.springcloud.system.department.dto.DepartmentDto;
import com.springcloud.system.department.entity.Department;
import com.springcloud.system.department.vo.DepartmentVO;

import java.util.List;

/**
 * 部门 Service
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/17 16:00
 **/
public interface DepartmentService {

    /**
     * 查询部门下拉列表
     *
     * @return List<SelectFormatVO>
     */
    List<SelectFormatVO> findDepartmentAll();

    /**
     *
     * 根据UserId查询部门
     * @param userId 运营平台用户
     *
     * @return Department
     */
    Department findDepartmentByUserId(Integer userId);

    /**
     * 分页查询
     * @param departmentDto Dto
     * @return DatePageVO<DepartmentVO>
     */
    DatePageVO<DepartmentVO> findDepartmentPage(DepartmentDto departmentDto);

    /**
     * 新增
     * @param departmentDto Dto
     */
    void addDepartment(DepartmentDto departmentDto);

    /**
     * 修改
     * @param departmentDto Dto
     */
    void editDepartment(DepartmentDto departmentDto);

    Boolean validateDepartmentNameRepeat(String DepartmentName, Integer id);

}
