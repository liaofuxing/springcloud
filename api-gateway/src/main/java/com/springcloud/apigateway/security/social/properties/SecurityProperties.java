package com.springcloud.apigateway.security.social.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author liaofuxing
 * @date 2019/03/12 12:23
 * @E-mail liaofuxing@outlook.com
 */
@Component
@ConfigurationProperties(prefix = "spring-social.security")
public class SecurityProperties {


    private SocialProperties social = new SocialProperties();

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }


}
