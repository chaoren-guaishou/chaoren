package com.wang.article.service.impl;

import com.wang.article.dto.po.Article;
import com.wang.article.dao.ArticleMapper;
import com.wang.article.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章信息表 服务实现类
 * </p>
 *
 * @author 超人
 * @since 2020-11-13
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
