package com.springcloud.system.role.etity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色表
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/16 20:25
 *
 **/
@Entity
@Table(name="role")
@Data
public class RoleInfo {

    @Id
    private Integer id;

    private String roleName;
}
