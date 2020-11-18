package com.wang.system.controller.api;

import cn.hutool.core.bean.BeanUtil;
import com.api.entities.SysMenuVO;
import com.api.entities.SysUserVO;
import com.api.feign.ISysFeign;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wang.system.dto.po.SysMenu;
import com.wang.system.dto.po.SysUser;
import com.wang.system.service.ISysMenuService;
import com.wang.system.service.ISysUserService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author DPJ
 * @since 2020/11/18
 */
@RestController
public class SysApi implements ISysFeign {

    @Resource
    private ISysUserService userService;

    @Resource
    private ISysMenuService menuService;

    @Override
    public SysUserVO searchUser(String userName) {
        LambdaQueryWrapper<SysUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysUser::getUsername, userName);
        SysUser sysUser = userService.getOne(queryWrapper);
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtil.copyProperties(sysUser, sysUserVO);
        return sysUserVO;
    }

    @Override
    public List<SysMenuVO> listMenu(String userId) {
        return menuService.listMenu(userId);
    }
}
