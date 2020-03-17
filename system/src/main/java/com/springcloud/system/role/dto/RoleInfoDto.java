package com.springcloud.system.role.dto;

import com.springcloud.common.entity.PageInfo;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色表
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/16 20:25
 **/
@Data
public class RoleInfoDto extends PageInfo {

    private Integer id;

    private String roleName;

    private String description;
}
