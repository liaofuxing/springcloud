package com.myspringcloud.apigateway.security.config;


import com.myspringcloud.apigateway.security.filter.JsonAuthenticationFilter;
import com.myspringcloud.apigateway.security.filter.TokenAuthenticationEntryPoint;
import com.myspringcloud.apigateway.security.filter.TokenAuthorizationFilter;
import com.myspringcloud.apigateway.security.securityhandle.DefaultAuthenticationFailureHandler;
import com.myspringcloud.apigateway.security.securityhandle.DefaultAuthenticationSuccessHandler;
import com.myspringcloud.apigateway.security.securityhandle.TokenAccessDeniedHandler;
import com.myspringcloud.apigateway.security.securityhandle.TokenLogoutSuccessHandler;
import com.myspringcloud.apigateway.security.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private UserDetailServiceImpl userDetailsService;

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
     * 默认登陆成功处理器
     */
    @Autowired
    private DefaultAuthenticationSuccessHandler defaultAuthenticationSuccessHandler;

    /**
     * 默认登陆失败处理器
     */
    @Autowired
    private DefaultAuthenticationFailureHandler defaultAuthenticationFailureHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
        //关闭session，不再使用
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .csrf().disable()
        //未登录结果处理
        .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
        .and()
        //权限不足结果处理
        .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
        .and()
        //权限设置管理
        .authorizeRequests()
        //放行以下url
        .antMatchers("/user/register","/users/test/*").permitAll()
        //给对应的url设置权限（只有ADMIN才可以访问，除去ROLE_前缀，spring帮我们处理了）
        //在数据库中用户的role字段是要加ROLE_的ROLE_ADMIN才可以匹配到这里的ADMIN
        .antMatchers("/user/lala/**").hasRole("ADMIN")
        //所有请求都需要授权（除了放行的）
        .anyRequest().authenticated()
        .and()
        //设置登出url
        .logout().logoutUrl("/user/logout")
        //设置登出成功处理器（下面介绍）
        .logoutSuccessHandler(logoutSuccessHandler)
        .and().cors().and()
        //开启登录
        .formLogin();

        //  http.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
        /*  authorizationFilter是用来拦截登录请求判断请求种是否带有token,并且token是否有对应的已经登录的用户,如果有应该直接授权通过
         *  所以这个过滤器应该在UsernamePasswordAuthenticationFilter过滤器之前执行,所以放在LogoutFilter之后
         */
        http.addFilterBefore(authorizationFilter, LogoutFilter.class);
        //用自定义的授权过滤器覆盖UsernamePasswordAuthenticationFilter
        http.addFilterAt(getJsonFilter(super.authenticationManager()), UsernamePasswordAuthenticationFilter.class);

    }

    /**
     * 获取json授权filter
     *
     * @return
     */
    private AbstractAuthenticationProcessingFilter getJsonFilter(AuthenticationManager authenticationManager) {
        AbstractAuthenticationProcessingFilter filter = new JsonAuthenticationFilter();

        // 登录成功后
        filter.setAuthenticationSuccessHandler(defaultAuthenticationSuccessHandler);
        // 登录失败后
        filter.setAuthenticationFailureHandler(defaultAuthenticationFailureHandler);
        // 作用在登录的URL
        filter.setFilterProcessesUrl("/user/login");
        // 设置验证manager
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }


	//密码加密器，在授权时，框架为我们解析用户名密码时，密码会通过加密器加密在进行比较
	//将密码加密器交给spring管理，在注册时，密码也是需要加密的，再存入数据库中
	//用户输入登录的密码用加密器加密，再与数据库中查询到的用户密码比较
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
        //设置使用自己实现的userDetailsService（loadUserByUsername）
        .userDetailsService(userDetailsService)
        //设置密码加密方式
        .passwordEncoder(bCryptPasswordEncoder());
    }
}