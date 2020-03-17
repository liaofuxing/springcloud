package com.springcloud.system.department.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table
@Entity
public class SystemUserDepartment {

    @Id
    private Integer id;

    private Integer systemUserId;

    private Integer departmentId;
}
