package com.wang.article.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wang.article.dto.po.Article;
import com.wang.article.dto.request.ArticleREQ;
import com.wang.article.service.IArticleService;
import com.wang.until.base.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章信息表 前端控制器
 * </p>
 *
 * @author 超人
 * @since 2020-11-13
 */
@RestController
@RequestMapping("/article/")
@Api(tags = "文章信息API")
public class ArticleController {

    /** 文章表 服务类 */
    private final IArticleService articleService;

    public ArticleController(IArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * <p>
     * 分页查询文章信息
     * </p>
     *
     * @param articleREQ article查询对象
     * @return 文章信息分页信息
     */
    @ApiOperation(value = "分页查询文章信息")
    @PostMapping("search/article/page")
    public BaseResult searchArticlePage(@RequestBody ArticleREQ articleREQ) {
        // 构建article查询条件
        LambdaQueryWrapper<Article> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper
                .eq(StringUtils.isNotBlank(articleREQ.getTitle()),
                        Article::getTitle, articleREQ.getTitle()) // 标题
                .eq(null != articleREQ.getStatus(),
                        Article::getStatus, articleREQ.getStatus()) // 状态
                .orderByDesc(Article::getUpdatedTime); // 根据修改时间降序排列

        // 分页查询文章信息,并返回
        return BaseResult.success(articleService.page(articleREQ.getPage(), queryWrapper));
    }
}
