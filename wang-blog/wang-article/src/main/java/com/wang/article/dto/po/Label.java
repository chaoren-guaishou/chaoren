package com.wang.article.dto.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 标签表
 * </p>
 *
 * @author 超人
 * @since 2020-11-13
 */
@Data
@ApiModel(value="Label对象", description="标签表")
public class Label {

    @ApiModelProperty("标签ID")
    private String id;

    @ApiModelProperty("分类id")
    private String categoryId;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("标签名称")
    private String name;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

}
