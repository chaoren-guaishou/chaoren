package com.wang.article.service.impl;

import com.wang.article.dto.po.ArticleLabel;
import com.wang.article.dao.ArticleLabelMapper;
import com.wang.article.service.IArticleLabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章标签中间表 服务实现类
 * </p>
 *
 * @author 超人
 * @since 2020-11-13
 */
@Service
public class ArticleLabelServiceImpl extends ServiceImpl<ArticleLabelMapper, ArticleLabel> implements IArticleLabelService {

}
