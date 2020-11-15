package com.wang.article.service;

import com.api.entities.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.article.dto.request.CategoryREQ;
import com.wang.until.base.BaseResult;

/**
 * 分类service
 *
 * @author dpj
 * @version 1.0
 * @date 2020/11/10
 */
public interface CategoryService extends IService<Category> {

    /**
     * <p>
     * 分页查询分类
     * </P>
     *
     * @param categoryREQ 查询参数
     * @return 分类分页对象
     */
    BaseResult queryPage(CategoryREQ categoryREQ);

    /**
     * <p>
     * 查询所有数据正常的分类信息(带标签)
     * </p>
     *
     * @return 所有数据正常的分类信息(带标签)
     */
    BaseResult searchNormalWithLabel();
}
