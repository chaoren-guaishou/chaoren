package com.wang.article.dto.request;

import com.wang.article.dto.po.Article;
import com.wang.until.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文章表 Article查询对象
 * </p>
 *
 * @author DPJ
 * @since 2020/11/16
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Article查询对象", description = "文章表")
@Data
public class ArticleREQ extends BaseRequest<Article> {

    @ApiModelProperty(value = "文章标题", required = true)
    private String title;

    @ApiModelProperty(value = "文章状态", required = true)
    private Integer status;

}
