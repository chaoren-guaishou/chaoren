package com.wang.article.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 标签表 更新对象
 * </p>
 *
 * @author DPJ
 * @since 2020/11/14
 */
@Data
@ApiModel(value = "Label更新对象", description = "标签表")
public class LabelUpdateREQ {

    @ApiModelProperty(value = "主键ID", required = true)
    private String id;

    @ApiModelProperty(value = "分类ID", required = true)
    private String categoryId;

    @ApiModelProperty(value = "标签名称", required = true)
    private String name;
}
