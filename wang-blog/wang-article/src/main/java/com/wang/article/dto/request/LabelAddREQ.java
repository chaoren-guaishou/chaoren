package com.wang.article.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 标签表 新增对象
 * </p>
 *
 * @author DPJ
 * @since 2020/11/14
 */
@Data
@ApiModel(value = "Label新增对象", description = "标签表")
public class LabelAddREQ {

    @ApiModelProperty(value = "分类ID", required = true)
    private String categoryId;

    @ApiModelProperty(value = "标签名称", required = true)
    private String name;
}
