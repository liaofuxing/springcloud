package com.springcloud.system.department.service.impl;



import com.springcloud.system.department.dao.DepartmentRepository;
import com.springcloud.system.department.entity.Department;
import com.springcloud.system.department.entity.SystemUserDepartment;
import com.springcloud.system.department.service.DepartmentService;
import com.springcloud.system.department.service.SystemUserDepartmentService;
import com.springcloud.system.role.vo.SelectFormatVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 部门 Service
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/16 20:25
 *
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private SystemUserDepartmentService systemUserDepartmentService;

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * 查询所有角色
     * @return
     */
    @Override
    public List<SelectFormatVO> findDepartmentAll() {
        List<SelectFormatVO> selectFormatVOList = new ArrayList();
        List<Department> departmentList = departmentRepository.findAll();
        for (Department department: departmentList) {
            SelectFormatVO selectFormatVO = new SelectFormatVO(department.getId(), department.getDepartmentName());
            selectFormatVOList.add(selectFormatVO);
        }
        return selectFormatVOList;
    }

    /**
     * 根据userId 查询角色
     * @param userId
     * @return
     */
    @Override
    public Department findDepartmentByUserId(Integer userId) {
        SystemUserDepartment systemUserDepartmentBySystemUserId = systemUserDepartmentService.findSystemUserDepartmentBySystemUserId(userId);
        Optional<Department> byId = departmentRepository.findById(systemUserDepartmentBySystemUserId.getDepartmentId());
        return byId.get();
    }
}
