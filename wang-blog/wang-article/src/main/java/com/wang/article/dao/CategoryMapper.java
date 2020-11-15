package com.wang.article.dao;

import com.api.entities.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.article.dto.response.CategoryWithLabelRES;

/**
 * 分类dao
 *
 * @author dpj
 * @version 1.0
 * @date 2020/11/10
 */
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * <p>
     * 查询所有数据正常的分类信息(带标签)
     * </p>
     *
     * @return 所有数据正常的分类信息(带标签)
     */
    CategoryWithLabelRES selectNormalWithLabel();
}
