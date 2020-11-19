package com.wang.oauth2.config;

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

    @Resource(name = "userDetailServiceImpl")
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
        // 关闭csrf攻击
        http.csrf().disable();
    }
}
