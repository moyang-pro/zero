package com.moyang.zero.manager;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.moyang.zero.common.enums.DelEnum;
import com.moyang.zero.dto.LoginInfo;
import com.moyang.zero.entity.BlogArticle;
import com.moyang.zero.mapper.BlogArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: moyang
 * @ClassName: BlogArticleManager
 * @Date: 2022/2/4 1:53
 * @Description: 博客文章信息管理
 * @Version: V1.0
 **/
@Component
@Slf4j
public class BlogArticleManager {

	@Resource
	BlogArticleMapper blogArticleMapper;

	public BlogArticle getBlogArticleById(Long blogId){
		if (blogId == null || blogId < 0){
			return null;
		}
		return new LambdaQueryChainWrapper<>(blogArticleMapper)
				.eq(BlogArticle::getId, blogId).eq(BlogArticle::getDelFlag, DelEnum.valid())
				.one();
	}

	public BlogArticle readBlogArticle(Long blogId, LoginInfo loginInfo){
		if (blogId == null || blogId < 0){
			return null;
		}
		// 校验权限 + 记录浏览
		return new LambdaQueryChainWrapper<>(blogArticleMapper)
				.eq(BlogArticle::getId, blogId).eq(BlogArticle::getDelFlag, DelEnum.valid())
				.one();
	}
}
