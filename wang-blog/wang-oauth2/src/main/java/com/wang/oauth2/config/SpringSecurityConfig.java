package com.wang.oauth2.config;

import com.wang.oauth2.handler.CustomAuthenticationFailureHandler;
import com.wang.oauth2.handler.CustomAuthenticationSuccessHandler;
import com.wang.oauth2.handler.CustomLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * <p>
 * 安全配置类
 * </p>
 *
 * @author DPJ
 * @since 2020/11/17
 */
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /** 自定义登陆成功处理器 */
    @Resource
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    /** 自定义登陆成功处理器 */
    @Resource
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    /** 自定义退出登陆成功处理器 */
    @Resource
    private CustomLogoutSuccessHandler logoutSuccessHandler;

    /** 用户信息 */
    @Resource(name = "userDetailServiceConfig")
    private UserDetailsService userDetailsService;

    /**
     * <p>
     * BCrypt加密方式
     * </p>
     *
     * @return BCrypt加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 读取用户信息进行验证
        auth.userDetailsService(userDetailsService);
    }

    /**
     * <p>
     * URI权限拦截
     * </p>
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 配置表单登陆
                .successHandler(authenticationSuccessHandler) // 成功处理器
                .failureHandler(authenticationFailureHandler) // 失败处理器
            .and()
                .logout() // 退出登陆成功处理器
                .logoutSuccessHandler(logoutSuccessHandler)
            .and()
                .csrf().disable(); // 关闭csrf攻击
    }
}
