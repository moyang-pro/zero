package com.moyang.zero.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moyang.zero.entity.BlogArticle;
import com.moyang.zero.entity.BlogArticleTags;

import java.util.List;

/**
 * <p>
 * 博客文章标签 服务类
 * </p>
 *
 * @author moyang
 * @since 2022-02-09
 */
public interface IBlogArticleTagsService extends IService<BlogArticleTags> {

	/**
	 *  保存or更新博客的标签
	 * @param tags 标签
	 * @param blogArticle 博客文章
	 */
	void saveOrUpdateTags(List<String> tags, BlogArticle blogArticle);

}
