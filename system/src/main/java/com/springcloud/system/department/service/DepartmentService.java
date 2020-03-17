package com.springcloud.system.department.service;


import com.springcloud.system.department.entity.Department;
import com.springcloud.system.role.vo.SelectFormatVO;

import java.util.List;

public interface DepartmentService {

    List<SelectFormatVO> findDepartmentAll();

    Department findDepartmentByUserId(Integer userId);
}
