package com.wang.system.dto.po;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author 超人
 * @since 2020-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysRole对象", description="角色信息表")
public class SysRole implements Serializable {


    @ApiModelProperty(value = "角色 ID")
    private String id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色说明")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

}
