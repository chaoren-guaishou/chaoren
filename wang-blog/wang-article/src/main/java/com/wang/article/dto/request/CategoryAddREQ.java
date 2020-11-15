package com.wang.article.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 新增分类对象
 *
 * @author dpj
 * @version 1.0
 * @date 2020/11/11
 */
@ApiModel("新增分类对象")
@Data
public class CategoryAddREQ {

    @ApiModelProperty(value = "名称", required = true)
    private String name;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("排序")
    private Integer sort;

}
