package com.wang.oauth2.controller;

import com.google.common.base.Preconditions;
import com.wang.oauth2.service.AuthService;
import com.wang.until.base.BaseResult;
import com.wang.until.tools.RequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 刷新令牌 API
 * </p>
 *
 * @author DPJ
 * @since 2020/11/23
 */
@RestController
public class AuthController {

    /** 自定义认证 服务类：用户刷新令牌 */
    @Resource
    private AuthService authService;

    /** 加密方式 */
    @Resource
    private PasswordEncoder passwordEncoder;

    /** 提供客户端详细信息 */
    @Resource
    private ClientDetailsService clientDetailsService;

    /** 请求头前缀 */
    private static final String HEADER_TYPE = "Basic ";

    @GetMapping("refreshToken")
    public BaseResult refreshToken(HttpServletRequest request) {
        try {
            // 获取请求中的刷新令牌指令
            String refreshToken = request.getParameter("refreshToken");
            Preconditions.checkArgument(StringUtils.isNotBlank(refreshToken), "刷新令牌不能为空！");

            // 请求头
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (null == header || !header.startsWith(HEADER_TYPE)) {
                throw new UnapprovedClientAuthenticationException("请求头中无client信息！");
            }

            String[] tokens = RequestUtil.extractAndDecodeHeader(header);
            if (tokens.length != 2) {
                throw new Exception("client信息错误！");
            }
            String clientId = tokens[0];
            String clientSecret = tokens[1];

            // 根据clientId获取客户端信息
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
            if (null == clientDetails) {
                throw new UnapprovedClientAuthenticationException("clientId对应的客户端信息不存在：" + clientId);
            }

            // 客户端密码是否正确
            if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
                throw new UnapprovedClientAuthenticationException("无效的clientSecret");
            }

            // 获取刷新令牌
            return authService.refreshToken(header, refreshToken);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
