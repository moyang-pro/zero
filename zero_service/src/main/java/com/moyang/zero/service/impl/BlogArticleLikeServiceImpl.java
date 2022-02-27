package com.moyang.zero.service.impl;

import com.moyang.zero.entity.BlogArticleLike;
import com.moyang.zero.mapper.BlogArticleLikeMapper;
import com.moyang.zero.service.IBlogArticleLikeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客文章点赞表 服务实现类
 * </p>
 *
 * @author moyang
 * @since 2022-02-28
 */
@Service
public class BlogArticleLikeServiceImpl extends ServiceImpl<BlogArticleLikeMapper, BlogArticleLike> implements IBlogArticleLikeService {

}
