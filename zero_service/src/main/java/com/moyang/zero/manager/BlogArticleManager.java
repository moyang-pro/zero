package com.moyang.zero.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moyang.zero.common.enums.DelEnum;
import com.moyang.zero.common.util.http.PageRequest;
import com.moyang.zero.common.util.http.PageResult;
import com.moyang.zero.dto.LoginInfo;
import com.moyang.zero.entity.BlogArticle;
import com.moyang.zero.mapper.BlogArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

	/**
	 * 获取作者的全部文章
	 * @param pageRequest 作者账号,分页信息
	 * @return 博客信息
	 */
	public PageResult<BlogArticle> getBlogListByAuthor(PageRequest<String> pageRequest) {
		String author = pageRequest.getData();
		if (StringUtils.isBlank(author)) {
			return PageResult.emptyList();
		}
		Page<BlogArticle> page = new Page<>(pageRequest.getPageIndex(), pageRequest.getPageSize());

		IPage<BlogArticle> articlePage = new LambdaQueryChainWrapper<>(blogArticleMapper)
				.eq(BlogArticle::getAuthor, author).eq(BlogArticle::getDelFlag, DelEnum.valid()).page(page);

		return PageResult.success(articlePage.getRecords(), articlePage.getTotal());
	}
}
