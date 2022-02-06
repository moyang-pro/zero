package com.moyang.zero.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moyang.zero.common.util.http.PageRequest;
import com.moyang.zero.common.util.http.PageResult;
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
	 * @return 结果 ID
	 */
	Result<Long> saveBlog(BlogSaveReq blog);

	/**
	 * 获取博客文章
	 * @param blogId 博客文章 ID
	 * @return 结果
	 */
	Result<BlogArticleVo> getBlogNoAuth(Long blogId);

	/**
	 * 获取作者的全部文章
	 * @param author 作者账号
	 * @return 博客信息
	 */
	PageResult<BlogArticleVo> getBlogListOfAuthor(PageRequest<String> author);

	/**
	 * 更新博客文章
	 * @param blog 博客文章信息(ID)
	 * @return 结果
	 */
	Result<Long> updateBlog(BlogSaveReq blog);

	/**
	 * 删除博客文章
	 * @param blogId 博客ID
	 * @param author 作者
	 * @return 结果
	 */
	Result<Boolean> deleteBlog(Long blogId, String author);
}
