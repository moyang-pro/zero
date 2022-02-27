package com.moyang.zero.service.impl;

import com.moyang.zero.entity.BlogArticleComment;
import com.moyang.zero.mapper.BlogArticleCommentMapper;
import com.moyang.zero.service.IBlogArticleCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客文章评论回复表 服务实现类
 * </p>
 *
 * @author moyang
 * @since 2022-02-28
 */
@Service
public class BlogArticleCommentServiceImpl extends ServiceImpl<BlogArticleCommentMapper, BlogArticleComment> implements IBlogArticleCommentService {

}
