package com.wang.oauth2.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wang.until.base.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * 自定义退出登陆成功处理器，删除Redis中token缓存数据
 * </p>
 *
 * @author DPJ
 * @since 2020/11/25
 */
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    private TokenStore tokenStore;

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Authentication authentication) throws IOException, ServletException {
        // 获得accessToken
        String accessToken = httpServletRequest.getParameter("accessToken");

        if (StringUtils.isNotBlank(accessToken)) {
            // 转换为token对象
            OAuth2AccessToken token = tokenStore.readAccessToken(accessToken);

            if (token != null) {
                // 删除Redis中的访问令牌
                tokenStore.removeAccessToken(token);
            }
        }

        // 响应结果
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(objectMapper.writeValueAsString(BaseResult.success().toString()));

    }
}
