package com.wang.article.controller;


import cn.hutool.core.bean.BeanUtil;
import com.wang.article.dto.po.Label;
import com.wang.article.dto.request.LabelAddREQ;
import com.wang.article.dto.request.LabelREQ;
import com.wang.article.dto.request.LabelUpdateREQ;
import com.wang.article.service.ILabelService;
import com.wang.until.base.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 标签表 前端控制器
 * </p>
 *
 * @author 超人
 * @since 2020-11-13
 */
@Api(tags = "标签API")
@RestController
@RequestMapping("/label/")
public class LabelController {

    /** 标签表 服务类 */
    private final ILabelService labelService;

    public LabelController(ILabelService labelService) {
        this.labelService = labelService;
    }

    /**
     * <p>
     * 分页查询标签
     * </p>
     *
     * @param labelREQ 标签表 查询对象
     * @return 标签 分页数据
     */
    @ApiOperation("标签管理分页查询接口")
    @PostMapping("page/label")
    public BaseResult pageLabel(@RequestBody LabelREQ labelREQ) {
        return labelService.pageLabel(labelREQ);
    }

    /**
     * <p>
     * 根据ID查询一条标签记录
     * </p>
     *
     * @param id 标签的ID
     * @return 标签对象
     */
    @ApiImplicitParam(name = "id", value = "标签的ID")
    @ApiOperation("根据ID查询一条标签记录")
    @GetMapping("search/one/{id}")
    public BaseResult searchOne(@PathVariable("id") String id) {
        return BaseResult.success(labelService.getById(id));
    }

    /**
     * <p>
     * 根据ID更新标签
     * </p>
     *
     * @param labelUpdateREQ 标签 更新对象
     * @return 标签 更新结果
     */
    @ApiOperation("更新标签")
    @PostMapping("update")
    public BaseResult update(@RequestBody LabelUpdateREQ labelUpdateREQ) {
        Label label = BeanUtil.copyProperties(labelUpdateREQ, Label.class);
        labelService.updateById(label);
        return BaseResult.success();
    }

    /**
     * <p>
     * 新增标签
     * </p>
     *
     * @param labelAddREQ 标签 新增对象
     * @return 标签 新增结果
     */
    @ApiOperation("新增标签")
    @PostMapping("save")
    public BaseResult save(@RequestBody LabelAddREQ labelAddREQ) {
        Label label = BeanUtil.copyProperties(labelAddREQ, Label.class);
        labelService.save(label);
        return BaseResult.success();
    }

    /**
     * <p>
     * 根据ID删除标签
     * </p>
     *
     * @param id 标签的ID
     * @return 标签 删除结果
     */
    @ApiImplicitParam(name = "id", value = "标签的ID", required = true)
    @ApiOperation("根据ID删除一条标签记录")
    @GetMapping("remove/one/{id}")
    public BaseResult removeOne(@PathVariable("id") String id) {
        return BaseResult.success(labelService.removeById(id));
    }
}
