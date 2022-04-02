package com.moyang.zero.controller;


import com.moyang.zero.auth.util.LoginContext;
import com.moyang.zero.common.util.http.Result;
import com.moyang.zero.service.IBlogFollowerService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.moyang.zero.common.constant.ApplicationConstant.ZERO_API;

/**
 * <p>
 * 博客粉丝表 前端控制器
 * </p>
 *
 * @author moyang
 * @since 2022-03-04
 */
@RestController
@RequestMapping(ZERO_API + "/blog-follower")
public class BlogFollowerController extends TemplateController {

	@Resource
	IBlogFollowerService blogFollowerService;

	@PostMapping("/follow")
	@RequiresRoles("COMMON_USER")
	@RequiresAuthentication
	@ApiOperation(value = "墨阳空间-博客系统关注博主")
	Result<Boolean> followAuthor(@RequestParam("author") String author){
		String emy = LoginContext.getCurrentUser().getEmy();
		return blogFollowerService.followAuthor(emy, author);
	}

	@PostMapping("/unfollow")
	@RequiresRoles("COMMON_USER")
	@RequiresAuthentication
	@ApiOperation(value = "墨阳空间-博客系统取消关注博主")
	Result<Boolean> unfollowAuthor(@RequestParam("author") String author){
		String emy = LoginContext.getCurrentUser().getEmy();
		return blogFollowerService.unfollowAuthor(emy, author);
	}
}
