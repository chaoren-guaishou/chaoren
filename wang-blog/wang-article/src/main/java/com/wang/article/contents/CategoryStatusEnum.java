package com.wang.article.contents;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 分类状态枚举类
 *
 * @author dpj
 * @version 1.0
 * @date 2020/11/12
 */
@AllArgsConstructor
@Getter
public enum CategoryStatusEnum {

    /** 正常1 */
    NORMAL(1);

    private final Integer status;
}
