package com.springcloud.mall.client.systemclient;

import com.springcloud.common.vo.ResultVO;
import com.springcloud.mall.commodity.entity.SystemUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author liaofuxing
 * @date 2019/03/08 2:40
 */

/**
 * 调用商城后台系统接口
 * name 表示访问那个应用的接口
 */
@FeignClient( name = "system", fallback = SystemClientFallback.class)
public interface SystemClient {

    /**
     *
     * 这是一个Feign示例方法
     *
     * Feign调用必须是全路径,而不是方法上的路径 (/systemUser/api/findSystemUserById)
     * @param id
     * @return
     */
    @GetMapping("/systemUser/api/findSystemUserById")
    ResultVO<SystemUser> findSystemUserById(@RequestParam Integer id);

    /**
     *
     * 这是一个Feign示例方法
     *
     * Feign调用必须是全路径,而不是方法上的路径 (/systemUser/api/findSystemUserById)
     * @param systemUserInfo
     * @return
     */
    @PostMapping("/systemUser/api/findSystemUser")
    ResultVO<SystemUser> findSystemUser(@RequestBody SystemUser systemUserInfo);

}
