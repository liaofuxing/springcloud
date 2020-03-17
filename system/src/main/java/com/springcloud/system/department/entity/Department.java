package com.springcloud.system.department.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 部门表
 */
@Data
@Table
@Entity
public class Department {

    @Id
    private Integer id;

    private String departmentName;

    private String description;

}
