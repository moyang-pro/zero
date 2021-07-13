package com.moyang.zero.controller;

import com.moyang.zero.common.exception.BusinessException;
import com.moyang.zero.utils.http.Result;
import com.moyang.zero.req.RegisterReq;
import com.moyang.zero.service.ISysMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 墨阳空间 ---平台---成员信息表 前端控制器
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@RestController
@RequestMapping("/sys-member")
public class SysMemberController extends TemplateController {

	@Resource
	ISysMemberService memberService;

	@PostMapping("/register")
	Result<Boolean> register(@RequestBody RegisterReq req){
         this.checkRegisterInfo(req);
		 if(memberService.registerNewMember(req)) {
		 	return Result.success();
		 }
		 return Result.fail();
	}

	/**
	 * 检查注册请求的参数
	 * @param req 注册请求
	 */
	private void checkRegisterInfo(RegisterReq req) {
		if (req == null){
			throw new BusinessException("数据异常，注册信息不能为空！");
		}
		if(StringUtils.isBlank(req.getEmy())){
			throw new BusinessException("数据异常，墨阳账号不能为空！");
		}
		if(StringUtils.isBlank(req.getPwd())){
			throw new BusinessException("数据异常，密码不能为空！");
		}
		if (StringUtils.isBlank(req.getPhone())){
			throw new BusinessException("数据异常，手机号不能为空");
		}
		if (StringUtils.isBlank(req.getCheckCode())){
			throw new BusinessException("数据异常，验证码不能为空");
		}
	}
}
