package com.moyang.zero.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moyang.zero.common.util.MyBeanCopier;
import com.moyang.zero.common.util.http.Result;
import com.moyang.zero.entity.BlogArticle;
import com.moyang.zero.manager.BlogArticleManager;
import com.moyang.zero.mapper.BlogArticleMapper;
import com.moyang.zero.req.BlogSaveReq;
import com.moyang.zero.service.IBlogArticleService;
import com.moyang.zero.vo.BlogArticleVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author moyang
 * @since 2022-02-02
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements IBlogArticleService {

	@Resource
	private BlogArticleManager blogArticleManager;

	@Override
	public Result<Long> saveBlog(BlogSaveReq blog) {
		BlogArticle blogArticle = new BlogArticle();
		blogArticle.setArticleTitle(blog.getTitle());
		blogArticle.setArticleDes(blog.getDes());
		blogArticle.setArticleContent(blog.getHtmlContent());
		blogArticle.setAuthor(blog.getAuthor());
		blogArticle.recordCreateInfo(blog.getAuthor(), "保存新文章");
		this.save(blogArticle);
		return Result.success(blogArticle.getId());
	}

	@Override
	public Result<BlogArticleVo> getBlogNoAuth(Long blogId) {
		BlogArticle blogArticle = blogArticleManager.getBlogArticleById(blogId);
		BlogArticleVo blogArticleVo = MyBeanCopier.copyBean(blogArticle, BlogArticleVo::new);
		blogArticleVo.setTitle(blogArticle.getArticleTitle());
		blogArticleVo.setAuthor(blogArticle.getAuthor());
		blogArticleVo.setDes(blogArticle.getArticleDes());
		blogArticleVo.setHtmlContent(blogArticle.getArticleContent());
		return Result.success(blogArticleVo);
	}
}
