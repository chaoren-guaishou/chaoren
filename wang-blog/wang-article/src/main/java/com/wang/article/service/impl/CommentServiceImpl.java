package com.wang.article.service.impl;

import com.wang.article.dto.po.Comment;
import com.wang.article.dao.CommentMapper;
import com.wang.article.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论信息表 服务实现类
 * </p>
 *
 * @author 超人
 * @since 2020-11-13
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
