package com.wang.oauth2.config;

import cn.hutool.core.collection.CollUtil;
import com.api.entities.SysMenuVO;
import com.api.entities.SysUserVO;
import com.api.feign.ISysFeign;
import com.wang.oauth2.po.JWTUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * UserDetailServiceImpl  加载用户
 * </p>
 *
 * @author DPJ
 * @since 2020/11/18
 */
@Service
@Slf4j
public class UserDetailServiceConfig implements UserDetailsService {

    @Resource
    private ISysFeign iSysFeign;

    @Override
    public JWTUser loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1.校验用户名
        if (StringUtils.isBlank(username)) {
            throw new BadCredentialsException("用户名不能为空！");
        }

        // 2.从数据库中获取用户信息
        SysUserVO sysUserVO = iSysFeign.searchUser(username);
        if (null == sysUserVO) {
            throw new BadCredentialsException("用户名或密码错误！");
        }
        log.info("==================用户信息获取成功,用户密码==========={}", sysUserVO.getPassword());

        // 3.获取用户所拥有的角色
        List<SysMenuVO> sysMenuVOS = iSysFeign.listMenu(sysUserVO.getId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (CollUtil.isNotEmpty(sysMenuVOS)) {
            sysMenuVOS.forEach(e ->
                    authorities.add(new SimpleGrantedAuthority(e.getCode())));
        }

        return new JWTUser(
                sysUserVO.getId(), sysUserVO.getUsername(),
                sysUserVO.getPassword(), sysUserVO.getNickName(),
                sysUserVO.getImageUrl(), sysUserVO.getMobile(),
                sysUserVO.getEmail(), sysUserVO.getIsAccountNonExpired(),
                sysUserVO.getIsAccountNonLocked(), sysUserVO.getIsCredentialsNonExpired(),
                sysUserVO.getIsEnabled(), authorities);
    }
}
