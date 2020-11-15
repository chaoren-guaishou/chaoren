package com.wang.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.article.dto.po.Label;
import com.wang.article.dao.LabelMapper;
import com.wang.article.dto.request.LabelREQ;
import com.wang.article.dto.response.LabelRES;
import com.wang.article.service.ILabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.until.base.BaseResult;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author 超人
 * @since 2020-11-13
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {

    @Override
    public BaseResult pageLabel(LabelREQ labelREQ) {
        Page<LabelRES> labelRESPage = this.baseMapper.pageLabel(labelREQ.getPage(), labelREQ);
        return BaseResult.success(labelRESPage);
    }
}
