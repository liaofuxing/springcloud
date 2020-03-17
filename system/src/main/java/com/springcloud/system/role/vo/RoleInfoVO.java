package com.springcloud.system.role.vo;

import com.springcloud.common.entity.PageInfo;
import lombok.Data;

/**
 * 角色表
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/17 15:24
 *
 **/
@Data
public class RoleInfoVO extends PageInfo {

    private Integer id;

    private String roleName;

    private String description;
}
