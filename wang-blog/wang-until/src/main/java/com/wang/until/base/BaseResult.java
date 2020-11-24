package com.wang.until.base;

import com.wang.until.constant.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 统一结果返回类
 *
 * @author dpj
 * @version 1.0
 * @date 2020/11/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseResult implements Serializable {

    /**
     * 业务响应编码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private Object data;

    public static BaseResult success() {
        return new BaseResult(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), null);
    }

    public static BaseResult success(String message) {
        return new BaseResult(ResultEnum.SUCCESS.getCode(), message, null);
    }

    public static BaseResult success(String message, Object data) {
        return new BaseResult(ResultEnum.SUCCESS.getCode(), message, data);
    }

    public static BaseResult success(Object data) {
        return new BaseResult(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    public static BaseResult failure() {
        return new BaseResult(ResultEnum.FAILURE.getCode(), ResultEnum.FAILURE.getMessage(), null);
    }

    public static BaseResult failure(String message) {
        return new BaseResult(ResultEnum.FAILURE.getCode(), message, null);
    }

    public static BaseResult failure(Integer code, String message) {
        return new BaseResult(code, message, null);
    }


}
