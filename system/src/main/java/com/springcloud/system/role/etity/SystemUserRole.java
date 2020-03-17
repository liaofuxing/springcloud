package com.springcloud.system.role.etity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 运营平台用户——角色中间表
 *
 * @author liaofuxing
 * @E-mail liaofuxing@outlook.com
 * @date 2020/03/16 20:25
 **/
@Entity
@Table
@Data
public class SystemUserRole {

    @Id
    private Integer id;

    private Integer systemUserId;

    private Integer roleId;

}
