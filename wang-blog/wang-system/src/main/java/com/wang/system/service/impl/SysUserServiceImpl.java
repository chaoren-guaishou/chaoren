package com.wang.system.service.impl;

import com.wang.system.dto.po.SysUser;
import com.wang.system.dao.SysUserMapper;
import com.wang.system.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 超人
 * @since 2020-11-17
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
