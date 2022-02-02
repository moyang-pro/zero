package com.moyang.zero.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moyang.zero.entity.BlogArticle;
import com.moyang.zero.mapper.BlogArticleMapper;
import com.moyang.zero.service.IBlogArticleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author moyang
 * @since 2022-02-02
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements IBlogArticleService {

}
