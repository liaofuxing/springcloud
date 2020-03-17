package com.springcloud.system.department.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 部门表和用户关系表
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/17 16:00
 **/
@Data
@Table
@Entity
public class SystemUserDepartment {

    @Id
    private Integer id;

    private Integer systemUserId;

    private Integer departmentId;
}
