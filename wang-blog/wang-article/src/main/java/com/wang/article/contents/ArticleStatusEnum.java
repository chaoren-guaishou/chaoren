package com.wang.article.contents;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 文章状态枚举类
 * </p>
 *
 * @author DPJ
 * @since 2020/11/16
 */
@AllArgsConstructor
@Getter
public enum ArticleStatusEnum {

    DELETE(1, "已删除"),

    WAIT(2, "待审核"),

    SUCCESS(3, "审核通过"),

    FAIL(4, "审核不通过");

    private final Integer code;

    private final String des;

}
