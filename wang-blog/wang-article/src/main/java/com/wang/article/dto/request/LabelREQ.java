package com.wang.article.dto.request;

import com.wang.article.dto.response.LabelRES;
import com.wang.until.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 标签表 Label查询对象
 * </p>
 *
 * @author DPJ
 * @since 2020/11/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="Label查询对象", description="标签表")
public class LabelREQ extends BaseRequest<LabelRES> {

    @ApiModelProperty("分类ID")
    private String categoryId;

    @ApiModelProperty("标签名称")
    private String name;
}
