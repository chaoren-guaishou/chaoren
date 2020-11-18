package com.wang.oauth2.po;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * JWTUser
 * </p>
 *
 * @author DPJ
 * @since 2020/11/18
 */
@Data
@ApiModel(value="JWTUser对象")
public class JWTUser implements UserDetails {

    @ApiModelProperty(value = "用户 ID")
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @JSONField(serialize = false)
    @ApiModelProperty(value = "密码，加密存储, admin/1234")
    private String password;

    @JSONField(serialize = false)
    @ApiModelProperty(value = "帐户是否过期(1 未过期，0已过期)")
    private boolean isAccountNonExpired;

    @JSONField(serialize = false)
    @ApiModelProperty(value = "帐户是否被锁定(1 未过期，0已过期)")
    private boolean isAccountNonLocked;

    @JSONField(serialize = false)
    @ApiModelProperty(value = "密码是否过期(1 未过期，0已过期)")
    private boolean isCredentialsNonExpired;

    @JSONField(serialize = false)
    @ApiModelProperty(value = "帐户是否可用(1 可用，0 删除用户)")
    private boolean isEnabled;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "头像url")
    private String imageUrl;

    @ApiModelProperty(value = "注册手机号")
    private String mobile;

    @ApiModelProperty(value = "注册邮箱")
    private String email;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

    @ApiModelProperty(value = "密码更新时间")
    private Date pwdUpdatedTime;

    /**
     * 用户所拥有的菜单标识
     */
    private List<GrantedAuthority> authorities;

    public JWTUser(String id, String username,
                   String password, Integer isAccountNonExpired,
                   Integer isAccountNonLocked, Integer isCredentialsNonExpired,
                   Integer isEnabled, String nickName,
                   String imageUrl, String mobile,
                   String email, Date createdTime,
                   Date updatedTime, Date pwdUpdatedTime,
                   List<GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAccountNonExpired = isAccountNonExpired == 1;
        this.isAccountNonLocked = isAccountNonLocked == 1;
        this.isCredentialsNonExpired = isCredentialsNonExpired == 1;
        this.isEnabled = isEnabled == 1;
        this.nickName = nickName;
        this.imageUrl = imageUrl;
        this.mobile = mobile;
        this.email = email;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.pwdUpdatedTime = pwdUpdatedTime;
        this.authorities = authorities;
    }

}
