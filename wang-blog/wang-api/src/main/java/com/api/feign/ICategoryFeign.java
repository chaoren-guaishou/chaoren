package com.api.feign;

import com.wang.until.base.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 标签Feign 服务类
 * </p>
 *
 * @author DPJ
 * @since 2020/11/17
 */
@FeignClient(name = "article-server", path = "/article")
@RestController
@RequestMapping("/category/")
public interface ICategoryFeign {

    /**
     * <p>
     * 查询所有状态正常的分类(带标签)
     * </p>
     *
     * @return 所有状态正常的分类(带标签)
     */
    @GetMapping("list/normal/with/label")
    BaseResult searchNormalCategoryAndLabel();
}
