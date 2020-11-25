package com.wang.oauth2.handler;

import cn.hutool.core.map.MapUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.oauth2.service.AuthService;
import com.wang.until.base.BaseResult;
import com.wang.until.tools.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * 自定义登陆成功处理器
 * </p>
 *
 * @author DPJ
 * @since 2020/11/24
 */
@Slf4j
@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /** 加密方式 */
    @Resource
    private PasswordEncoder passwordEncoder;

    /** JSON 转换工具类 */
    @Resource
    private ObjectMapper objectMapper;

    /** 客户端信息 */
    @Resource
    private ClientDetailsService clientDetailsService;

    /** 认证服务类 创建token */
    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    /** 请求头前缀 */
    private static final String HEADER_TYPE = "Basic ";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        log.info("登陆成功：{}", authentication.getPrincipal());

        // 获得请求头 authorization
        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("header:{}", header);

        // 校验请求头中是否存在客户端信息
        if (null == header || !header.startsWith(HEADER_TYPE)) {
            throw new UnapprovedClientAuthenticationException("请求头无客户端信息！");
        }

        // 解析请求头
        String[] tokens = RequestUtil.extractAndDecodeHeader(header);
        if (tokens.length != 2) {
            throw new UnapprovedClientAuthenticationException("请求头中客户端信息错误！");
        }
        String clientId = tokens[0];
        String clientSecret = tokens[1];

        // 根据clientId获取客户端信息
        ClientDetails clientDetails = clientDetailsService
                .loadClientByClientId(clientId);

        // 校验密码(clientSecret)是否正确
        if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
            throw new UnapprovedClientAuthenticationException("无效的clientSecret！");
        }

        // 构建tokenRequest
        TokenRequest tokenRequest = new TokenRequest(
                MapUtil.empty(), clientId,
                clientDetails.getScope(), "custom");

        // 构建oauth2Request
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

        // 构建OAuth2Authentication   oauth2认证凭证
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(
                oAuth2Request, authentication);

        // 获取accessToken，根据认证凭证去创建与访问凭证相关的令牌-需要提供认证凭证
        OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices
                .createAccessToken(oAuth2Authentication);

        // 响应结果
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(objectMapper.writeValueAsString(BaseResult.success(oAuth2AccessToken)));

    }
}
