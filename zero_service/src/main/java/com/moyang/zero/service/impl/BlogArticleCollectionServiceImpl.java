package com.moyang.zero.service.impl;

import com.moyang.zero.entity.BlogArticleCollection;
import com.moyang.zero.mapper.BlogArticleCollectionMapper;
import com.moyang.zero.service.IBlogArticleCollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客文章收藏表 服务实现类
 * </p>
 *
 * @author moyang
 * @since 2022-02-28
 */
@Service
public class BlogArticleCollectionServiceImpl extends ServiceImpl<BlogArticleCollectionMapper, BlogArticleCollection> implements IBlogArticleCollectionService {

}
