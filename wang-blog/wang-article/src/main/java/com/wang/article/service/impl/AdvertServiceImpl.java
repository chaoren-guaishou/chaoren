package com.wang.article.service.impl;

import com.wang.article.dto.po.Advert;
import com.wang.article.dao.AdvertMapper;
import com.wang.article.service.IAdvertService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 广告信息表 服务实现类
 * </p>
 *
 * @author 超人
 * @since 2020-11-12
 */
@Service
public class AdvertServiceImpl extends ServiceImpl<AdvertMapper, Advert> implements IAdvertService {

}
