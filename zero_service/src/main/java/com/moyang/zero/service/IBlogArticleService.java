package com.moyang.zero.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moyang.zero.common.util.http.PageRequest;
import com.moyang.zero.common.util.http.PageResult;
import com.moyang.zero.common.util.http.Result;
import com.moyang.zero.dto.LoginInfo;
import com.moyang.zero.entity.BlogArticle;
import com.moyang.zero.req.BlogPublishReq;
import com.moyang.zero.req.BlogSaveReq;
import com.moyang.zero.vo.BlogArticleVo;

import java.util.List;

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
	 * 获取博客文章
	 * @param blogId 博客文章 ID
	 * @param author 作者账号
	 * @return 结果
	 */
	Result<BlogArticleVo> getBlogOfAuthor(Long blogId, String author);


	/**
	 * 用户读博客文章
	 * @param blogId 博客文章 ID
	 * @param loginInfo 登录用户信息
	 * @return 结果
	 */
	Result<BlogArticleVo> readBlogArticle(Long blogId, LoginInfo loginInfo);

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

	/**
	 * 发布博客文章
	 * @param blogPublishReq 文章发布信息
	 * @return 博客ID
	 */
	Result<Long> publishBlog(BlogPublishReq blogPublishReq);

	/**
	 * 获取博客首页TAB的文章列表
	 * @param pageRequest tabType 分栏信息
	 * @return 文章信息列表
	 */
	PageResult<BlogArticleVo> getBlogHomeList(PageRequest<String> pageRequest);

	/**
	 * 获取博客首页TOP的文章列表
	 * @return 文章信息列表
	 */
	List<BlogArticle> getHomeTopBlogList();
}
