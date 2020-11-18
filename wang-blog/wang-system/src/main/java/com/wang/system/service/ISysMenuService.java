package com.wang.system.service;

import com.api.entities.SysMenuVO;
import com.wang.system.dto.po.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单信息表 服务类
 * </p>
 *
 * @author 超人
 * @since 2020-11-17
 */
public interface ISysMenuService extends IService<SysMenu> {

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
