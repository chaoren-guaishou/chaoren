package com.wang.article.contents;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消息枚举类：用于自定义消息
 *
 * @author dpj
 * @version 1.0
 * @date 2020/11/11
 */
@AllArgsConstructor
@Getter
public enum MessageEnum {

    /**
     * 当前分类不存在，或已被删除！
     */
    NO_CATEGORY("当前分类不存在，或已被删除！");

    private final String msg;
}
