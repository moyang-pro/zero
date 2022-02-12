package com.moyang.zero.controller;


import com.moyang.zero.auth.util.LoginContext;
import com.moyang.zero.common.enums.BlogPublishTypeEnum;
import com.moyang.zero.common.enums.BlogTypeEnum;
import com.moyang.zero.common.exception.BusinessException;
import com.moyang.zero.common.util.http.PageRequest;
import com.moyang.zero.common.util.http.PageResult;
import com.moyang.zero.common.util.http.Result;
import com.moyang.zero.req.BlogPublishReq;
import com.moyang.zero.req.BlogSaveReq;
import com.moyang.zero.service.IBlogArticleService;
import com.moyang.zero.vo.BlogArticleVo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.moyang.zero.common.constant.ApplicationConstant.ZERO_API;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author moyang
 * @since 2022-02-02
 */
@RestController
@RequestMapping(ZERO_API +"/blog-article")
public class BlogArticleController extends TemplateController {

	@Resource
	IBlogArticleService blogArticleService;

	@PostMapping("/publish")
	@ApiOperation(value = "墨阳空间-博客文章发布")
	@RequiresRoles("COMMON_USER")
	@RequiresAuthentication
	Result<Long> publishBlog(@RequestBody BlogPublishReq blogPublishReq){
		String author = LoginContext.getCurrentUser().getEmy();
		BlogSaveReq articleInfo = blogPublishReq.getArticleInfo();
		if (articleInfo == null){
			return Result.fail("博客文章信息为空！");
		}
		articleInfo.setAuthor(author);
		checkPublishParams(blogPublishReq);
		return blogArticleService.publishBlog(blogPublishReq);
	}

	@PostMapping("/save")
	@ApiOperation(value = "墨阳空间-博客文章保存")
	@RequiresRoles("COMMON_USER")
	@RequiresAuthentication
	Result<Long> saveBlog(@RequestBody BlogSaveReq blogSaveReq){
		String author = LoginContext.getCurrentUser().getEmy();
		// 以后端为主
		blogSaveReq.setAuthor(author);
		checkParams(blogSaveReq);
		return blogArticleService.saveBlog(blogSaveReq);
	}

	@PostMapping("/update")
	@ApiOperation(value = "墨阳空间-博客文章更新")
	@RequiresRoles("COMMON_USER")
	@RequiresAuthentication
	Result<Long> editBlog(@RequestBody BlogSaveReq blogSaveReq){
		String author = LoginContext.getCurrentUser().getEmy();
		// 校验权限
		if (!author.equals(blogSaveReq.getAuthor())) {
			return Result.fail("权限不足，用户只能编辑自己的文章！");
		}
		checkParams(blogSaveReq);
		if (blogSaveReq.getId() == null || blogSaveReq.getId() < 0){
			return Result.fail("文章ID错误！");
		}
		return blogArticleService.updateBlog(blogSaveReq);
	}

	@GetMapping("/delete")
	@ApiOperation(value = "墨阳空间-博客文章删除（更新为无效文章）")
	@RequiresRoles("COMMON_USER")
	@RequiresAuthentication
	Result<Boolean> deleteBlog(@RequestParam("id") Long blogId){
		String author = LoginContext.getCurrentUser().getEmy();
		if (blogId == null || blogId < 0){
			return Result.fail("文章ID错误！");
		}
		return blogArticleService.deleteBlog(blogId, author);
	}

	@GetMapping("/look")
	@ApiOperation(value = "墨阳空间-博客文章浏览（游客）")
	Result<BlogArticleVo> getBlog(@RequestParam("id") Long blogId){
		if (blogId == null || blogId <= 0) {
			return Result.fail("参数错误：id");
		}
		return blogArticleService.getBlogNoAuth(blogId);
	}

	@GetMapping("/read")
	@ApiOperation(value = "墨阳空间-博客文章浏览(博客系统用户)")
	@RequiresRoles("COMMON_USER")
	@RequiresAuthentication
	Result<BlogArticleVo> readBlog(@RequestParam("id") Long blogId){
		if (blogId == null || blogId <= 0) {
			return Result.fail("参数错误：id");
		}
		return blogArticleService.readBlogArticle(blogId, LoginContext.getCurrentUser());
	}


	@GetMapping("/read/me")
	@ApiOperation(value = "墨阳空间-博客文章浏览(自己)")
	@RequiresRoles("COMMON_USER")
	@RequiresAuthentication
	Result<BlogArticleVo> getMyBlog(@RequestParam("id") Long blogId){
		if (blogId == null || blogId <= 0) {
			return Result.fail("参数错误：id");
		}
		String author = LoginContext.getCurrentUser().getEmy();
		return blogArticleService.getBlogOfAuthor(blogId, author);
	}

	@PostMapping("/myList")
	@ApiOperation(value = "墨阳空间-博客文章列表")
	@RequiresRoles("COMMON_USER")
	@RequiresAuthentication
	PageResult<BlogArticleVo> getMyBlogList(@RequestBody PageRequest<String> pageRequest){
		String author = LoginContext.getCurrentUser().getEmy();
		pageRequest.setData(author);
		return blogArticleService.getBlogListOfAuthor(pageRequest);
	}

	private void checkParams(BlogSaveReq blogSaveReq) {
		if (blogSaveReq == null) {
			throw new BusinessException("参数为空！");
		}
		if (StringUtils.isBlank(blogSaveReq.getTitle())) {
			throw new BusinessException("文章标题为空");
		}
		if (StringUtils.isBlank(blogSaveReq.getDes())) {
			throw new BusinessException("文章概述为空");
		}
		if (StringUtils.isBlank(blogSaveReq.getHtmlContent())) {
			throw new BusinessException("文章内容为空");
		}
	}

	private void checkPublishParams(BlogPublishReq blogPublishReq) {

		if (blogPublishReq == null) {
			throw new BusinessException("参数为空！");
		}
		if (blogPublishReq.isHasCover() && StringUtils.isBlank(blogPublishReq.getCoverUrl())) {
			throw new BusinessException("文章封面为空");
		}
		if (CollectionUtils.isEmpty(blogPublishReq.getTags())) {
			throw new BusinessException("文章标签为空");
		}
		if (!BlogTypeEnum.isInEnum(blogPublishReq.getType())) {
			throw new BusinessException("文章类型参数异常");
		}
		if (!BlogPublishTypeEnum.isInEnum(blogPublishReq.getPublishType())) {
			throw new BusinessException("文章发布形式参数异常");
		}
		if (blogPublishReq.getType() != BlogTypeEnum.DEFAULT.getCode()
				&& StringUtils.isBlank(blogPublishReq.getQuote())) {
			throw new BusinessException("转载或翻译的原文链接为空");
		}
		checkParams(blogPublishReq.getArticleInfo());
	}
}
