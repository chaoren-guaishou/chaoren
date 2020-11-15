package com.wang.until.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回结果状态码枚举类
 *
 * @author dpj
 * @version 1.0
 * @date 2020/11/8
 */
@AllArgsConstructor
@Getter
public enum ResultEnum {

    SUCCESS(200, "成功"),

    FAILURE(500, "失败"),

    UNAUTHENTICATED(401, "请先通过身份认证"),

    AUTH_FAIL(1400, "认证失败"),

    // token异常
    TOKEN_PAST(1401, "身份过期，请求重新登录！"),

    TOKEN_ERROR(1402, "令牌错误"),

    HEADER_ERROR(1403, "请求头错误"),

    AUTH_USERNAME_NONE(1405, "用户名不能为空"),

    AUTH_PASSWORD_NONE(1406, "密码不能为空"),

    MENU_NO(306, "没此权限，请联系管理员！");

    private final Integer code;

    private final String message;
}
