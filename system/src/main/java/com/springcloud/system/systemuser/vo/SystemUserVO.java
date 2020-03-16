package com.springcloud.system.systemuser.vo;

import com.springcloud.common.entity.PageInfo;
import lombok.Data;


/**
 * 系统用户(运营平台用户)VO
 * @author liaofuxing
 * @date 2020/03/13 22:01
 */

@Data
public class SystemUserVO extends PageInfo {

    /**
     * 系统用户id
     */
    private Integer id;


    /**
     * 账号
     */
    private String account;

    /**
     * 系统用户用户名
     */
    private String username;

    /**
     * 系统用户密码
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 地址
     */
    private String address;

    /**
     * 电话
     */
    private String phone;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 所属部门id
     */
    private Integer departmentId;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 创建时间
     */
    private String creationTime;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 性别 对应值
     */
    private String genderLabel;

}
