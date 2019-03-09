package myspringcloud.malluser.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


/**
 * @author liaofuxing
 * @date 2019/03/09 16:51
 *
 * 商城用户
 */
@Data
@Entity
public class MallUser {

    @Id
    String id;

    /**
     * 商城用户名
     */
    private String username;

    /**
     * 商城帐号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮箱
     */
    private String e_mail;

}


