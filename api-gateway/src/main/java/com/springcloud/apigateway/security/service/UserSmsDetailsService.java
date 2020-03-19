package com.springcloud.apigateway.security.service;

import com.springcloud.apigateway.security.entity.SecurityUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserSmsDetailsService {
    SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException;
}
