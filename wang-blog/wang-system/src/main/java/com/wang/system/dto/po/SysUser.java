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
 * 用户信息表
 * </p>
 *
 * @author 超人
 * @since 2020-11-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
@ApiModel(value="SysUser对象", description="用户信息表")
public class SysUser implements Serializable {


    @ApiModelProperty(value = "用户 ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码，加密存储, admin/1234")
    private String password;

    @ApiModelProperty(value = "帐户是否过期(1 未过期，0已过期)")
    private Integer isAccountNonExpired;

    @ApiModelProperty(value = "帐户是否被锁定(1 未过期，0已过期)")
    private Integer isAccountNonLocked;

    @ApiModelProperty(value = "密码是否过期(1 未过期，0已过期)")
    private Integer isCredentialsNonExpired;

    @ApiModelProperty(value = "帐户是否可用(1 可用，0 删除用户)")
    private Integer isEnabled;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "头像url")
    private String imageUrl;

    @ApiModelProperty(value = "注册手机号")
    private String mobile;

    @ApiModelProperty(value = "注册邮箱")
    private String email;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createdTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    @ApiModelProperty(value = "密码更新时间")
    private Date pwdUpdatedTime;


}
