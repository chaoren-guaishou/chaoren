package com.api.feign;

import com.api.entities.SysMenuVO;
import com.api.entities.SysUserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 用户信息Feign 服务类
 * </p>
 *
 * @author DPJ
 * @since 2020/11/18
 */
@RequestMapping("/sys-user")
@FeignClient(name = "system-server", path = "/system")
public interface ISysFeign {

    /**
     * <p>
     * 根据用户名查询用户信息
     * </p>
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @GetMapping("search/user/{userName}")
    SysUserVO searchUser(@PathVariable("userName") String userName);

    /**
     * <p>
     * 根据用户ID获取用户所拥有的权限
     * </p>
     *
     * @param userId 用户ID
     * @return 用户ID下的所有菜单
     */
    @GetMapping("list/menu/{userId}")
    List<SysMenuVO> listMenu(@PathVariable("userId") String userId);

}
