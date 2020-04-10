package com.springcloud.generatecode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GenerateCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenerateCodeApplication.class, args);
    }

}
