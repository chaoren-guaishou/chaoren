package com.wang.article.controller;

import cn.hutool.core.bean.BeanUtil;
import com.api.entities.Category;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wang.article.contents.CategoryStatusEnum;
import com.wang.article.contents.MessageEnum;
import com.wang.article.dto.request.CategoryAddREQ;
import com.wang.article.dto.request.CategoryREQ;
import com.wang.article.dto.request.CategoryUpdateREQ;
import com.wang.article.service.CategoryService;
import com.wang.until.base.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 分类API
 *
 * @author dpj
 * @version 1.0
 * @date 2020/11/10
 */

@RestController
@RequestMapping("/category/")
@Api(tags = "分类API")
public class CategoryController {

    /**
     * 分类service
     */
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * <p>
     * 分类分页查询
     * </p>
     *
     * @param categoryREQ 查询参数
     * @return 分类列表
     */
    @ApiOperation(value = "分类分页查询")
    @PostMapping("search")
    public BaseResult search(@RequestBody CategoryREQ categoryREQ) {
        return categoryService.queryPage(categoryREQ);
    }

    /**
     * 新增分类
     *
     * @param categoryAddREQ 新增分类对象
     * @return 执行结果
     */
    @ApiOperation(value = "新增分类")
    @PostMapping("save")
    public BaseResult save(@RequestBody CategoryAddREQ categoryAddREQ) {
        Category category = BeanUtil.copyProperties(categoryAddREQ, Category.class);
        categoryService.save(category);
        return BaseResult.success();
    }

    /**
     * 通过ID查询分类信息
     *
     * @param id 要查询分类的ID
     * @return 根据ID查询出的分类实体
     */
    @ApiOperation(value = "通过ID查询分类信息")
    @GetMapping("search/{id}")
    public BaseResult searchById(@PathVariable ("id") String id) {
        return BaseResult.success(categoryService.getById(id));
    }

    /**
     * 通过ID更新分类信息
     *
     * @param categoryUpdateREQ 分类更新对象
     * @return 更新结果
     */
    @ApiOperation(value = "通过ID更新分类信息")
    @PostMapping("update")
    public BaseResult update(@RequestBody CategoryUpdateREQ categoryUpdateREQ) {
        // 先根据ID查询分类
        Category oldCategory = categoryService.getById(categoryUpdateREQ.getId());
        if (BeanUtil.isEmpty(oldCategory)) {
            return BaseResult.failure(MessageEnum.NO_CATEGORY.getMsg());
        }

        Category category = BeanUtil.copyProperties(categoryUpdateREQ, Category.class);
        categoryService.updateById(category);

        return BaseResult.success();
    }

    /**
     * 通过ID更新分类信息
     *
     * @param id 分类的ID
     * @return 删除结果
     */
    @ApiOperation(value = "通过ID更新分类信息")
    @GetMapping("delete/{id}")
    public BaseResult delete(@PathVariable("id") String id) {
        categoryService.removeById(id);
        return BaseResult.success();
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
