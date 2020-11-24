package com.wang.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 令牌管理配置类，token管理
 * </p>
 *
 * @author DPJ
 * @since 2020/11/18
 */
@Configuration
public class JwtTokenStoreConfig {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

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
        return new JwtTokenStore(jwtAccessTokenConverter()) {

            // 将令牌储存到Redis中
            @Override
            public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
                // 将JWT的唯一标识jti作为key存入Redis中，并保持与原JWT有一致的时效性。
                if (token.getAdditionalInformation().containsKey("jti")) {
                    // 获取JWT的唯一标识jti
                    String jti = token.getAdditionalInformation().get("jti").toString();

                    // (K, V, 过期时间, 时间单位)
                    redisTemplate.opsForValue()
                            .set(jti, token.getValue(), token.getExpiresIn(), TimeUnit.SECONDS);
                }
                super.storeAccessToken(token, authentication);
            }

            // 刷新令牌或过期时，删除Redis中的令牌
            @Override
            public void removeAccessToken(OAuth2AccessToken token) {
                if (token.getAdditionalInformation().containsKey("jti")) {
                    // 获取JWT的唯一标识jti
                    String jti = token.getAdditionalInformation().get("jti").toString();

                    // 删除令牌
                    redisTemplate.delete(jti);
                }
                super.removeAccessToken(token);
            }
        };
    }

}
