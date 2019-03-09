package myspringcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author liaofuxing
 * @date 2019/03/08 2:40
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class MallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }

}
