package myspringcloud.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 商城用户
 */
@Data
public class SystemUserInfo{

    /**
     * 商城用户id
     */
    @NotNull(message = "primary is not null")
    private String id;


    /**
     * 账号
     */
    private String account;

    /**
     * 商城用户用户名
     */
    private String username;

    /**
     * 商城用户密码
     */
    private String password;

}
