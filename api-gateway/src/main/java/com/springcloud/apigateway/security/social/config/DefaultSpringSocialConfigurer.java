package com.springcloud.apigateway.security.social.config;


import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * SpringSocialConfigurer配置类
 * @author liaofuxing
 * @date 2019/03/15 0:18
 * @E-mail liaofuxing@outlook.com
 */
public class DefaultSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    public DefaultSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter)super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }
}
