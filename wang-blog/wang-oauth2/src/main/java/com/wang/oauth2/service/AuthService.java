package com.wang.oauth2.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.wang.until.base.BaseResult;
import com.wang.until.constant.ResultEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 自定义认证 服务类：用于刷新令牌
 * </p>
 *
 * @author DPJ
 * @since 2020/11/23
 */
@Service
public class AuthService {

    /** oauth2 服务名称 */
    @Value("${spring.application.name}")
    private String applicationName;

    /** oauth2 服务请求前缀 */
    @Value("${server.servlet.context-path}")
    private String contextPath;

    /** 负载均衡client */
    @Resource
    private LoadBalancerClient loadBalancerClient;

    public BaseResult refreshToken(String header, String refreshToken) throws HttpProcessException {

        // 采用客户端负载均衡，从Nacos获取认证服务的IP和端口号
        ServiceInstance serviceInstance = loadBalancerClient.choose(applicationName);
        if (null == serviceInstance) {
            return BaseResult.failure("未找到有效认证服务！");
        }
        // 请求刷新令牌url
        String refreshTokenUrl = serviceInstance
                .getUri().toString()
                .concat(contextPath)
                .concat("/oauth/token");

        // 封装刷新令牌请求参数
        Map<String, Object> map = new HashMap<>();
        map.put("grant_type", "refresh_token");
        map.put("refresh_token", refreshToken);

        // 构建请求参数(网址、请求参数、编码、client)
        Header[] headers = HttpHeader
                .custom() // 自定义请求
                .contentType(HttpHeader.Headers.APP_FORM_URLENCODED) // 数据类型
                .authorization(header) // 认证请求头
                .build();
        HttpConfig httpConfig = HttpConfig
                .custom()
                .headers(headers)
                .url(refreshTokenUrl)
                .map(map);

        // 发送请求，相应令牌
        String token = HttpClientUtil.post(httpConfig);
        JSONObject jsonToken = JSON.parseObject(token);
        if (StringUtils.isNotBlank(jsonToken.getString("error"))) {
            return BaseResult
                    .failure(ResultEnum.TOKEN_PAST.getCode(), ResultEnum.TOKEN_PAST.getMessage());
        }

        return BaseResult.success(jsonToken);
    }
}
