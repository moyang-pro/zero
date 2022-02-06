package com.moyang.zero.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moyang.zero.common.enums.DelEnum;
import com.moyang.zero.common.util.MyBeanCopier;
import com.moyang.zero.common.util.http.PageRequest;
import com.moyang.zero.common.util.http.PageResult;
import com.moyang.zero.common.util.http.Result;
import com.moyang.zero.entity.BlogArticle;
import com.moyang.zero.manager.BlogArticleManager;
import com.moyang.zero.mapper.BlogArticleMapper;
import com.moyang.zero.req.BlogSaveReq;
import com.moyang.zero.service.IBlogArticleService;
import com.moyang.zero.vo.BlogArticleVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

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
		blogArticle.setArticleText(blog.getTextContent());
		blogArticle.setAuthor(blog.getAuthor());
		blogArticle.recordCreateInfo(blog.getAuthor(), "保存新文章");
		this.save(blogArticle);
		return Result.success(blogArticle.getId());
	}

	@Override
	public Result<BlogArticleVo> getBlogNoAuth(Long blogId) {
		BlogArticle blogArticle = blogArticleManager.getBlogArticleById(blogId);
		if (blogArticle == null) {
			return Result.fail("无效的博客文章ID！");
		}
		BlogArticleVo blogArticleVo = MyBeanCopier.copyBean(blogArticle, BlogArticleVo::new);
		blogArticleVo.setTitle(blogArticle.getArticleTitle());
		blogArticleVo.setAuthor(blogArticle.getAuthor());
		blogArticleVo.setDes(blogArticle.getArticleDes());
		blogArticleVo.setHtmlContent(blogArticle.getArticleContent());
		blogArticleVo.setTextContent(blogArticle.getArticleText());
		return Result.success(blogArticleVo);
	}

	@Override
	public PageResult<BlogArticleVo> getBlogListOfAuthor(PageRequest<String> pageRequest) {
		PageResult<BlogArticle> articlePage = blogArticleManager
				.getBlogListByAuthor(pageRequest);
		List<BlogArticle> articleList = articlePage.getList();
		if (CollectionUtils.isEmpty(articleList)){
			return PageResult.emptyList();
		}
		List<BlogArticleVo> blogArticleVoList = MyBeanCopier.copyList(articleList, BlogArticleVo::new);
		for (int i =0; i< articleList.size(); i++) {
			blogArticleVoList.get(i).setTitle(articleList.get(i).getArticleTitle());
			blogArticleVoList.get(i).setAuthor(articleList.get(i).getAuthor());
			blogArticleVoList.get(i).setDes(articleList.get(i).getArticleDes());
			blogArticleVoList.get(i).setHtmlContent(articleList.get(i).getArticleContent());
			blogArticleVoList.get(i).setTextContent(articleList.get(i).getArticleText());
		}
		return PageResult.success(blogArticleVoList, articlePage.getTotal());
	}

	@Override
	public Result<Long> updateBlog(BlogSaveReq blog) {
		BlogArticle blogArticle = blogArticleManager.getBlogArticleById(blog.getId());
		if (blogArticle == null){
			return Result.fail("博客信息不存在！");
		}
		blogArticle.setArticleTitle(blog.getTitle());
		blogArticle.setArticleDes(blog.getDes());
		blogArticle.setArticleContent(blog.getHtmlContent());
		blogArticle.setArticleText(blog.getTextContent());
		blogArticle.setAuthor(blog.getAuthor());
		blogArticle.recordUpdateInfo(blog.getAuthor(), "更新文章信息");
		this.updateById(blogArticle);
		return Result.success();
	}

	@Override
	public Result<Boolean> deleteBlog(Long blogId, String author) {
		BlogArticle blogArticle = blogArticleManager.getBlogArticleById(blogId);
		if (blogArticle == null){
			return Result.fail("博客信息不存在！");
		}
		blogArticle.setDelFlag(DelEnum.deleted());
		blogArticle.recordUpdateInfo(author, "删除文章信息");
		this.updateById(blogArticle);
		return Result.success();
	}

}
