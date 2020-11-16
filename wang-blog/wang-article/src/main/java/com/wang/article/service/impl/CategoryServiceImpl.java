package com.wang.article.service.impl;

import com.api.entities.Category;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.article.dao.CategoryMapper;
import com.wang.article.dto.request.CategoryREQ;
import com.wang.article.dto.response.CategoryWithLabelRES;
import com.wang.article.service.CategoryService;
import com.wang.until.base.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类service实现类
 *
 * @author dpj
 * @version 1.0
 * @date 2020/11/10
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public BaseResult queryPage(CategoryREQ categoryREQ) {

        // 创建查询条件对象
        LambdaQueryWrapper<Category> queryWrapper = Wrappers.lambdaQuery();

        // 组装查询条件
        queryWrapper
                .like(StringUtils.isNotBlank(categoryREQ.getName()),
                        Category::getName, categoryREQ.getName()) // 分类名称
                .eq(categoryREQ.getStatus() != null,
                        Category::getStatus, categoryREQ.getStatus()) // 状态
                .orderByDesc(Category::getStatus) // 根据状态降序排序
                .orderByAsc(Category::getSort); // 根据sort升序排序

        // 根据条件执行分页查询
        IPage<Category> categoryIPage = this.baseMapper.selectPage(categoryREQ.getPage(), queryWrapper);

        return BaseResult.success(categoryIPage);
    }

    @Override
    public BaseResult searchNormalWithLabel() {
        List<CategoryWithLabelRES> categoryWithLabelRESList = this.baseMapper.selectNormalWithLabel();
        return BaseResult.success(categoryWithLabelRESList);
    }
}
