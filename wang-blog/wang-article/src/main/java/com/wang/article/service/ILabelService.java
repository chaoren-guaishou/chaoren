package com.wang.article.service;

import com.wang.article.dto.po.Label;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.article.dto.request.LabelREQ;
import com.wang.until.base.BaseResult;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author 超人
 * @since 2020-11-13
 */
public interface ILabelService extends IService<Label> {

    /**
     * <p>
     * 分页查询标签数据
     * </p>
     *
     * @param labelREQ 标签 查询对象
     * @return 标签 分页数据
     */
    BaseResult pageLabel(LabelREQ labelREQ);
}
