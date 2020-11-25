package com.wang.oauth2.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 授权服务器配置类
 * </p>
 *
 * @author DPJ
 * @since 2020/11/19
 */
@Slf4j
@Configuration
@EnableAuthorizationServer // 开启了授权服务器
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 数据源
     */
    @Resource
    private DataSource dataSource;

    /**
     * 用户信息加载类
     * 用来支持刷新token
     */
    @Resource(name = "userDetailServiceConfig")
    private UserDetailsService userDetailsService;

    /**
     * token管理方式->在JWTTokenStoreConfig中注入到SpringIOC容器中
     */
    @Resource
    private TokenStore tokenStore;

    /**
     * 令牌增强
     * 用于添加扩展信息，如用户信息等
     */
    @Resource(name = "jwtTokenEnhancer")
    private TokenEnhancer tokenEnhancer;

    /**
     * JWT转换器
     */
    @Resource(name = "jwtAccessTokenConverter")
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * <p>
     * <-1->提供客户端详细信息->使用JDBC管理，--需要在数据库中提前配置好客户端信息--
     * </p>
     *
     * @return ClientDetailsService
     */
    @Bean
    public ClientDetailsService jdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 认证管理器->在SpringSecurityConfig中注入到SpringIOC容器中
     * 用来支持password模式
     */
    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * <p>
     * <-2->配置被允许访问此授权服务器的客户端信息->数据库方式
     * </p>
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 读取客户端配置
        clients.withClientDetails(jdbcClientDetailsService());
        log.info("读取客户端配置成功。。。。。。。。。。。。。。。。");
    }

    /**
     * <p>
     * 授权服务器端点配置
     * </p>
     *
     * @param endpoints 授权服务器端点
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 添加令牌增强配置
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> tokenEnhancerList = new ArrayList<>();
        tokenEnhancerList.add(tokenEnhancer);
        tokenEnhancerList.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(tokenEnhancerList);

        endpoints
                .authenticationManager(authenticationManager) // 认证管理器  password模式需要
                .userDetailsService(userDetailsService) // 刷新令牌时需要
                .tokenStore(tokenStore).accessTokenConverter(jwtAccessTokenConverter) // 令牌(token)的管理方式
                .tokenEnhancer(enhancerChain).accessTokenConverter(jwtAccessTokenConverter); // 令牌增强器
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // /oauth/check_token 解析令牌，默认情况下拒绝访问
        security.checkTokenAccess("permitAll()");
    }
}
