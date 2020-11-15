package com.wang.until.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一请求类
 *
 * @author dpj
 * @version 1.0
 * @date 2020/11/8
 */
@Data
@ApiModel("统一请求类")
public class BaseRequest<T> implements Serializable {

    @ApiModelProperty(value = "当前页", required = true)
    private Integer current;

    @ApiModelProperty(value = "每页数量", required = true)
    private Integer size;

    @ApiModelProperty(hidden = true)
    public IPage<T> getPage() {
        return new Page<>(this.current, this.size);
    }

}
