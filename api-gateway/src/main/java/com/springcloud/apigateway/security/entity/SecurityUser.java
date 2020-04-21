package com.springcloud.apigateway.security.entity;

import com.springcloud.apigateway.securityuser.malluser.entity.MallUser;
import com.springcloud.apigateway.securityuser.systemuser.entity.SystemUser;
import com.springcloud.apigateway.securityuser.systemuser.entity.SystemUserRole;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@ToString
public class SecurityUser implements UserDetails {

    private String username;

    private String password;

    private Integer enabledFlag;

    private Collection<? extends  GrantedAuthority> authorities;



    public SecurityUser(MallUser mallUser) {
        this.username = mallUser.getUsername();
        this.password = mallUser.getPassword();
        this.authorities = Collections.singleton(new SimpleGrantedAuthority("role"));


    }

    public SecurityUser(SystemUser systemUser, SystemUserRole systemUserRole) {
        this.username = systemUser.getUsername();
        this.password = systemUser.getPassword();
        this.enabledFlag = systemUser.getEnabledFlag();
        this.authorities = Collections.singleton(new SimpleGrantedAuthority(systemUserRole.getRoleId().toString()));
    }

    public SecurityUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.authorities = Collections.singleton(new SimpleGrantedAuthority("role"));
    }

    private static final long serialVersionUID = 1L;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        String username = this.getUsername();
//        if (username != null) {
//            //SimpleGrantedAuthority authority = new SimpleGrantedAuthority(username);
//            authorities.add(this.authorities);
//        }
        return authorities;
    }

    //账户是否未过期,过期无法验证
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //指定用户是否解锁,锁定的用户无法进行身份验证
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //指示是否已过期的用户的凭据(密码),过期的凭据防止认证
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否可用 ,禁用的用户不能身份验证
    @Override
    public boolean isEnabled() {
        return this.enabledFlag != 1;
    }
}
