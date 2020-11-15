package com.wang.article.dto.response;

import com.api.entities.Category;
import com.wang.article.dto.po.Label;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * <p>
 * 分类返回对象(带标签)
 * </p>
 *
 * @author DPJ
 * @since 2020/11/15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "分类返回对象", description = "分类带标签")
public class CategoryWithLabelRES extends Category {

    private List<Label> labelList;
}
