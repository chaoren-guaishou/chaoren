package com.wang.article.dto.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文章标签中间表
 * </p>
 *
 * @author 超人
 * @since 2020-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("article_label")
@ApiModel(value="ArticleLabel对象", description="文章标签中间表")
public class ArticleLabel implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "文章 id")
    private String articleId;

    @ApiModelProperty(value = "标签id")
    private String labelId;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;


}
