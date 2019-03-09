package system.systemuser.dto;

import lombok.Data;

import javax.persistence.Id;

/**
 * 商城用户
 */

@Data
public class SystemUserDto {

    /**
     * 系统用户用户id
     */
    private String id;

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

}
