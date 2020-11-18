package com.wang.system.service.impl;

import com.api.entities.SysMenuVO;
import com.wang.system.dto.po.SysMenu;
import com.wang.system.dao.SysMenuMapper;
import com.wang.system.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单信息表 服务实现类
 * </p>
 *
 * @author 超人
 * @since 2020-11-17
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Override
    public List<SysMenuVO> listMenu(String userId) {
        return this.baseMapper.listMenu(userId);
    }
}
