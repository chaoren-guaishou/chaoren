package com.wang.article.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wang.article.dto.po.Label;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.article.dto.request.LabelREQ;
import com.wang.article.dto.response.LabelRES;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author 超人
 * @since 2020-11-13
 */
public interface LabelMapper extends BaseMapper<Label> {

    /**
     * <p>
     * 分页查询标签数据
     * </p>
     *
     * @param page 分页对象
     * @param labelREQ 标签 查询对象
     * @return 标签分页数据
     */
    Page<LabelRES> pageLabel(@Param("page") IPage<LabelRES> page,
                             @Param("labelREQ") LabelREQ labelREQ);
}
