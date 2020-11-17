package com.wang.system.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 全局填充配置类
 *
 * @author dpj
 * @version 1.0
 * @date 2020/11/10
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final String CREATED_TIME = "createdTime";

    private static final String UPDATED_TIME = "updatedTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName(CREATED_TIME, new Date(), metaObject);
        setFieldValByName(UPDATED_TIME, new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName(UPDATED_TIME, new Date(), metaObject);
    }
}
