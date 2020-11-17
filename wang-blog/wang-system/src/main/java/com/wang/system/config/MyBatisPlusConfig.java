package com.wang.system.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatisPlus分页配置类
 *
 * EnableTransactionManagement：开启事务管理
 * @author dpj
 * @version 1.0
 * @date 2020/11/10
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.wang.system.dao")
public class MyBatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
