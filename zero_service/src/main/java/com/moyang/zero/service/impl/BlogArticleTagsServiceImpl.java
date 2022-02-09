package com.moyang.zero.service.impl;

import com.moyang.zero.entity.BlogArticleTags;
import com.moyang.zero.mapper.BlogArticleTagsMapper;
import com.moyang.zero.service.IBlogArticleTagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客文章标签 服务实现类
 * </p>
 *
 * @author moyang
 * @since 2022-02-09
 */
@Service
public class BlogArticleTagsServiceImpl extends ServiceImpl<BlogArticleTagsMapper, BlogArticleTags> implements IBlogArticleTagsService {

}
