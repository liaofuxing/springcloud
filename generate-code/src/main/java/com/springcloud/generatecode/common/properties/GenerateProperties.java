package com.springcloud.generatecode.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("generate")
public class GenerateProperties {

    private String generateFileRootPath;

}
