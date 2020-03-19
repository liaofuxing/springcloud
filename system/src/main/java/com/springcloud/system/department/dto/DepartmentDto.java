package com.springcloud.system.department.dto;

import com.springcloud.common.entity.PageInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 部门 DTO
 *
 * @author liaofuxing
 *
 * @E-mail liaofuxing@outlook.com
 *
 * @date 2020/03/19 13:33
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class DepartmentDto extends PageInfo {

    private Integer id;

    private String departmentName;

    private String description;

}
