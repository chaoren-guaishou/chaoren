package com.wang.article.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 修改分类对象
 *
 * @author dpj
 * @version 1.0
 * @date 2020/11/11
 */
@Data
@ApiModel("修改分类对象")
public class CategoryUpdateREQ {

    @ApiModelProperty(value = "主键ID", required = true)
    private String id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("状态1正常，0禁用")
    private Integer status;

}
