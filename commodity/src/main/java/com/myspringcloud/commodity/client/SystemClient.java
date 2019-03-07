package com.myspringcloud.commodity.client;

import com.myspringcloud.commodity.entity.SystemUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调用商城后台系统接口
 * name 表示访问那个应用的接口
 */
@FeignClient( name = "system")
public interface SystemClient {

    /**
     *
     * 这是一个Feign实例方法
     *
     * Feign调用必须是全路径,而不是方法上的路径 (/systemUser/findSystemUserById)
     * @param id
     * @return
     */
    @GetMapping("/systemUser/findSystemUserById")
    SystemUser findSystemUserById(@RequestParam String id);
}
