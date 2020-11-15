package com.wang.article.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 标签 返回对象
 * </p>
 *
 * @author DPJ
 * @since 2020/11/13
 */
@Data
@ApiModel(value = "Label返回对象", description = "标签")
public class LabelRES {

    @ApiModelProperty("标签ID")
    private String id;

    @ApiModelProperty("分类id")
    private String categoryId;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("标签名称")
    private String name;

    @ApiModelProperty("创建时间")
    private Date createdTime;

    @ApiModelProperty("更新时间")
    private Date updatedTime;
}
