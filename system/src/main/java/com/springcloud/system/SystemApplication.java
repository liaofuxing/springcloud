package com.springcloud.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author liaofuxing
 * @date 2019/03/08 2:40
 */
@EnableTransactionManagement
@SpringBootApplication
@EnableEurekaClient
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

}
