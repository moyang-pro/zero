package com.moyang.zero.controller;


import com.moyang.zero.common.enums.AppCodeEnum;
import com.moyang.zero.entity.ZeroBanner;
import com.moyang.zero.service.IZeroBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static com.moyang.zero.common.constant.ApplicationConstant.ZERO_API;

/**
 * <p>
 * 墨阳空间广告表 前端控制器
 * </p>
 *
 * @author moyang
 * @since 2022-03-03
 */
@RestController
@RequestMapping(ZERO_API + "/zero-banner")
public class ZeroBannerController extends TemplateController {

	@Resource
	IZeroBannerService zeroBannerService;

	@GetMapping("/page/blog/banner")
	@ApiOperation(value = "墨阳空间-博客系统首页top文章列表")
	List<ZeroBanner> getHomeBlogTopList(){
		return zeroBannerService.getHomeTopBannerList(AppCodeEnum.BLOG_CODE.getCode());
	}
}
