package com.wang.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * <p>
 * 令牌管理配置类，token管理
 * </p>
 *
 * @author DPJ
 * @since 2020/11/18
 */
@Configuration
public class JWTTokenStoreConfig {

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();

        // 采用非对称加密jwt
        KeyStoreKeyFactory factory = new KeyStoreKeyFactory(
                new ClassPathResource("oauth2.jks"), "oauth2".toCharArray());
        converter.setKeyPair(factory.getKeyPair("oauth2"));
        return converter;
    }

    /**
     * <p>
     * token的管理方式：基于JWT
     * </p>
     *
     * @return JWT管理token
     */
    @Bean
    public TokenStore tokenStore() {
        // Jwt管理令牌
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

}
