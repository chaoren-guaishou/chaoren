package com.wang.oauth2.config;

import com.alibaba.fastjson.JSON;
import com.wang.oauth2.po.JWTUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 令牌增强配置类
 * </p>
 *
 * @author DPJ
 * @since 2020/11/23
 */
@Slf4j
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        // 扩展令牌内容
        final Map<String, Object> map = new LinkedHashMap<>();
        JWTUser jwtUser = (JWTUser) authentication.getPrincipal();
        map.put("userInfo", JSON.toJSON(jwtUser));

        // 设置附加信息
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
        log.info("令牌增强accessToken:{}", accessToken);
        return accessToken;
    }
}
