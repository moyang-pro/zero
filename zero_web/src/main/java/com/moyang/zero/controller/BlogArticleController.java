package com.moyang.zero.controller;


import com.moyang.zero.auth.util.LoginContext;
import com.moyang.zero.common.exception.BusinessException;
import com.moyang.zero.common.util.http.Result;
import com.moyang.zero.req.BlogSaveReq;
import com.moyang.zero.service.IBlogArticleService;
import com.moyang.zero.vo.BlogArticleVo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
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

	@PostMapping("/save")
	@ApiOperation(value = "墨阳空间-博客文章保存")
	@RequiresRoles("COMMON_USER")
	@RequiresAuthentication
	Result<Long> saveBlog(@RequestBody BlogSaveReq blogSaveReq){
		String author = LoginContext.getCurrentUser().getEmy();
		blogSaveReq.setAuthor(author);
		checkParams(blogSaveReq);
		return blogArticleService.saveBlog(blogSaveReq);
	}

	@GetMapping("/read")
	@ApiOperation(value = "墨阳空间-博客文章浏览")
	@RequiresRoles("COMMON_USER")
	@RequiresAuthentication
	Result<BlogArticleVo> getBlog(@RequestParam("id") Long blogId){
		if (blogId == null || blogId <= 0) {
			return Result.fail("参数错误：id");
		}
		return blogArticleService.getBlogNoAuth(blogId);
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
}
