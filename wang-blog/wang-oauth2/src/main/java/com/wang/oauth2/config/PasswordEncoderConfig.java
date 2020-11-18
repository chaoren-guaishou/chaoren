package com.wang.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <p>
 * 加密方式配置
 * </p>
 *
 * @author DPJ
 * @since 2020/11/18
 */
@Configuration
public class PasswordEncoderConfig {

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
}
