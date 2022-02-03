package com.moyang.zero.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moyang.zero.common.util.http.Result;
import com.moyang.zero.entity.BlogArticle;
import com.moyang.zero.req.BlogSaveReq;
import com.moyang.zero.vo.BlogArticleVo;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author moyang
 * @since 2022-02-02
 */
public interface IBlogArticleService extends IService<BlogArticle> {

	/**
	 * 保存博客文章
	 * @param blog 博客文章信息
	 * @return 结果
	 */
	Result<Long> saveBlog(BlogSaveReq blog);

	/**
	 * 获取博客文章
	 * @param blogId 博客文章 ID
	 * @return 结果
	 */
	Result<BlogArticleVo> getBlogNoAuth(Long blogId);

}
