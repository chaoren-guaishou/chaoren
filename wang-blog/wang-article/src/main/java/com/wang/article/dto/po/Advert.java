package com.wang.article.dto.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 广告信息表
 * </p>
 *
 * @author 超人
 * @since 2020-11-12
 */
@Data
public class Advert {

    /**
     * 主键
     */
    private String id;

    /**
     * 广告标题
     */
    private String title;

    /**
     * 广告图片
     */
    private String imageUrl;

    /**
     * 广告链接
     */
    private String advertUrl;

    /**
     * 广告跳转方式（_blank：新窗口打开，_self：当前窗口打开）
     */
    private String advertTarget;

    /**
     * 广告位置(1:首页轮播)
     */
    private Integer position;

    /**
     * 状态(1:正常，0:禁用)
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;


}
