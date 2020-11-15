package com.wang.article.api;

import com.api.entities.Category;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wang.article.contents.CategoryStatusEnum;
import com.wang.article.service.CategoryService;
import com.wang.until.base.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 分类Controller-无需认证
 * </p>
 *
 * @author DPJ
 * @since 2020/11/15
 */
@RestController
@RequestMapping("/api/category/")
@Api(tags = "分类Controller-无需认证")
public class ApiCategoryController {

    /** 标签表 服务类 */
    private final CategoryService categoryService;

    public ApiCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 查询所有数据正常的分类
     *
     * @return 所有数据正常的分类列表
     */
    @ApiOperation(value = "查询所有数据正常的分类")
    @GetMapping("list/normal")
    public BaseResult listNormalCategory() {
        LambdaQueryWrapper<Category> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Category::getStatus, CategoryStatusEnum.NORMAL.getStatus());
        return BaseResult.success(categoryService.list(queryWrapper));
    }

    /**
     * <p>
     * 查询所有数据正常的分类(带标签)
     * </p>
     *
     * @return 所有数据正常的分类信息(带标签)
     */
    @ApiOperation(value = "查询所有数据正常的分类(带标签)")
    @GetMapping("list/normal/with/label")
    public BaseResult searchNormalCategoryAndLabel() {
        return categoryService.searchNormalWithLabel();
    }

}
