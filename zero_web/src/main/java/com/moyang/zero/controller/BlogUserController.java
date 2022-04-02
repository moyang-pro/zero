package com.moyang.zero.controller;


import com.moyang.zero.auth.util.LoginContext;
import com.moyang.zero.common.util.http.PageRequest;
import com.moyang.zero.common.util.http.PageResult;
import com.moyang.zero.service.IBlogUserService;
import com.moyang.zero.vo.BlogUserVo;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.moyang.zero.common.constant.ApplicationConstant.ZERO_API;

/**
 * <p>
 * 博客系统用户表 前端控制器
 * </p>
 *
 * @author moyang
 * @since 2022-03-05
 */
@RestController
@RequestMapping(ZERO_API + "/blog-user")
public class BlogUserController extends TemplateController {

	@Resource
	IBlogUserService blogUserService;

	@PostMapping("/page/home/user")
	@ApiOperation(value = "墨阳空间-博客系统首页TAB页文章列表")
	PageResult<BlogUserVo> getHomePopUserList(@RequestBody PageRequest<String> pageRequest){
		return blogUserService.getHomePopUserList(pageRequest);
	}

	@GetMapping("/page/blog/author")
	@ApiOperation(value = "墨阳空间-博客系统文章阅读 作者信息查询")
	@RequiresRoles("COMMON_USER")
	@RequiresAuthentication
	BlogUserVo getBlogAuthorInfo(@RequestParam("author") String author){
		return blogUserService.getBlogAuthorInfo(author, LoginContext.getCurrentUser());
	}

}
