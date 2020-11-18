package com.wang.system.dao;

import com.api.entities.SysMenuVO;
import com.wang.system.dto.po.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单信息表 Mapper 接口
 * </p>
 *
 * @author 超人
 * @since 2020-11-17
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * <p>
     * 根据用户ID获取用户所拥有的权限
     * </p>
     *
     * @param userId 用户ID
     * @return 用户ID下的所有菜单
     */
    List<SysMenuVO> listMenu(String userId);
}
