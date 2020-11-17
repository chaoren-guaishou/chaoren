package com.wang.question.controller;

import com.api.feign.ICategoryFeign;
import com.wang.until.base.BaseResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 问题表 API
 * </p>
 *
 * @author DPJ
 * @since 2020/11/17
 */
@Api(tags = "问题API")
@RestController
@RequestMapping("/question/")
@Slf4j
public class QuestionController {

    private final ICategoryFeign categoryFeign;

    public QuestionController(ICategoryFeign categoryFeign) {
        this.categoryFeign = categoryFeign;
    }

    @GetMapping("test")
    public BaseResult test() {
        return categoryFeign.searchNormalCategoryAndLabel();
    }

}
