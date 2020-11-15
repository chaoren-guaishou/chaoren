package com.api.entities;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * 表 category实体类
 *
 * @author dpj
 * @version 1.0
 * @date 2020/11/9
 */
@Data
public class Category {
    
    /** 主键ID */
    private String id;

    /** 名称 */
    private String name;

    /** 备注 */
    private String remark;

    /** 状态1正常，0禁用 */
    private Integer status;

    /** 排序 */
    private Integer sort;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

}
