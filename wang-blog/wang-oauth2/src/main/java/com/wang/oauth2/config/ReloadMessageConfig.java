package com.wang.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * <p>
 * 加载中文的认证提示信息配置类
 * </p>
 *
 * @author DPJ
 * @since 2020/11/20
 */
@Configuration
public class ReloadMessageConfig {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new
                ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:");
        return messageSource;
    }

}
