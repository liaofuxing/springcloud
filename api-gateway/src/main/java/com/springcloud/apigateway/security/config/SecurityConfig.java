package com.springcloud.apigateway.security.config;


import com.springcloud.apigateway.security.filter.TokenAuthenticationEntryPoint;
import com.springcloud.apigateway.security.filter.TokenAuthorizationFilter;
import com.springcloud.apigateway.security.provider.SystemUserAuthenticationProvider;
import com.springcloud.apigateway.security.provider.UserSmsAuthenticationProvider;
import com.springcloud.apigateway.security.securityhandle.TokenAccessDeniedHandler;
import com.springcloud.apigateway.security.securityhandle.TokenLogoutSuccessHandler;
import com.springcloud.apigateway.security.service.SystemUserDetailsService;
import com.springcloud.apigateway.security.service.UserSmsDetailsService;
import com.springcloud.apigateway.security.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.social.security.SpringSocialConfigurer;


/**
 * Security主配置类
 *
 * @author liaofuxing
 * @date 2020/02/18 11:00
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] excludedAuthPages = {"/sms/*",
            "/user/register","/css/**",
            "/js/**",
            "/images/**",
            "/webjars/springfox-swagger-ui/**"
    };

    @Autowired
    private UserDetailServiceImpl userDetailsServiceImpl;

    @Autowired
    private UserSmsDetailsService userSmsDetailsServiceImpl;

    @Autowired
    private SystemUserDetailsService systemUserDetailsServiceImpl;

    @Autowired
    private SystemUserAuthenticationProvider systemUserAuthenticationProvider;

    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    @Autowired
    private UserSmsAuthenticationProvider userSmsAuthenticationProvider;

    /**
     * 权限鉴定过滤器
     */
    @Autowired
    private TokenAuthorizationFilter authorizationFilter;

    /**
     * 未登录结果处理
     */
    @Autowired
    private TokenAuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 权限不足结果处理
     */
    @Autowired
    private TokenAccessDeniedHandler accessDeniedHandler;

    /**
     * 用户注销成功处理器
     */
    @Autowired
    private TokenLogoutSuccessHandler logoutSuccessHandler;


    /**
     * 社交登录配置器
     */
    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    /**
     * json {"username":"","password":""}登录配置器
     */
    @Autowired
    private JsonAuthenticationConfigurer jsonAuthenticationConfigurer;

    /**
     * 短信验证码登录配置器
     */
    @Autowired
    private SmsCodeAuthenticationConfigurer smsCodeAuthenticationConfigurer;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring(). antMatchers("/swagger-ui.html")
                .antMatchers("/webjars/**")
                .antMatchers("/v2/**")
                .antMatchers("/swagger-resources/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //处理跨域请求
            http
                .cors().and().csrf().disable()
                .apply(jsonAuthenticationConfigurer)
                .and()
                .apply(springSocialConfigurer)
                .and()
                .apply(smsCodeAuthenticationConfigurer)
                .and()
                //权限不足结果处理
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler)
                .and()
                //设置登出url
                .logout().logoutUrl("/user/logout")
                //设置登出成功处理器（下面介绍）
                .logoutSuccessHandler(logoutSuccessHandler).and()
                .authorizeRequests()
                .antMatchers(excludedAuthPages).permitAll()
                .antMatchers("/user/lala/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated();

        /*  authorizationFilter是用来拦截登录请求判断请求中是否带有token,并且token是否有对应的已经登录的用户,如果有应该直接授权通过
         *  所以这个过滤器应该在UsernamePasswordAuthenticationFilter过滤器之前执行,所以放在LogoutFilter之后
         */
        http.addFilterAfter(authorizationFilter, LogoutFilter.class);
    }

    //配置多个authenticationProvider
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .authenticationProvider(systemUserAuthenticationProvider)
                .authenticationProvider(userSmsAuthenticationProvider);
                //.authenticationProvider(daoAuthenticationProvider)


    }


    //密码加密器，在授权时，框架为我们解析用户名密码时，密码会通过加密器加密在进行比较
    //将密码加密器交给spring管理，在注册时，密码也是需要加密的，再存入数据库中
    //用户输入登录的密码用加密器加密，再与数据库中查询到的用户密码比较
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImpl);
        return daoAuthenticationProvider;
    }

    @Bean
    public UserSmsAuthenticationProvider UserSmsAuthenticationProvider(){

        UserSmsAuthenticationProvider userSmsAuthenticationProvider = new UserSmsAuthenticationProvider();
        userSmsAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        userSmsAuthenticationProvider.setUserDetailsService(userSmsDetailsServiceImpl);
        return userSmsAuthenticationProvider;
    }

    @Bean
    public SystemUserAuthenticationProvider systemUserAuthenticationProvider(){

        SystemUserAuthenticationProvider systemUserAuthenticationProvider = new SystemUserAuthenticationProvider();
        systemUserAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        systemUserAuthenticationProvider.setUserDetailsService(systemUserDetailsServiceImpl);
        return systemUserAuthenticationProvider;
    }



}
