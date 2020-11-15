package com.wang.article.dto.request;

import com.api.entities.Category;
import com.wang.until.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分类请求类
 *
 * @author dpj
 * @version 1.0
 * @date 2020/11/10
 */

@ApiModel("分类请求类")
@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryREQ extends BaseRequest<Category> {

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("状态")
    private Integer status;

}
