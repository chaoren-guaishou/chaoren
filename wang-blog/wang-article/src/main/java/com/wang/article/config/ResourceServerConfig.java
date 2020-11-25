package com.wang.article.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

/**
 * <p>
 * 资源服务器配置类
 * </p>
 *
 * @author DPJ
 * @since 2020/11/25
 */
@Configuration
@EnableResourceServer // 标识为资源服务器，请求服务器中的资源，就要有token，无token或token无效，则无法访问
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法级别的权限控制
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Resource
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        // 指定令牌管理方式
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .sessionManagement()
                // 不会创建也不会使用HttpSession实例，应为使用token方式
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                // 授权规则配置
                .authorizeRequests()

                // 放行swagger-ui相关请求
                .antMatchers("/v2/api-docs",
                        "/v2/feign-docs", "/swagger-resources/configuration/ui",
                        "/swagger-resources", "/swagger-resources/configuration/security",
                        "/swagger-ui.html", "/webjars/**").permitAll()

                // 放行api开头的接口
                .antMatchers("/api/**").permitAll()

                // 所有请求都需要all范围(scope)
                .antMatchers("/**").access("#oauth2.hasScope('all')")

                // 其他的请求都需要先通过验证
                .anyRequest().authenticated();
    }
}
