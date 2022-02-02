package com.moyang.zero.controller;

import com.moyang.zero.auth.util.JwtUtil;
import com.moyang.zero.common.exception.BusinessException;
import com.moyang.zero.common.util.http.Result;
import com.moyang.zero.entity.auth.SysMemberDetail;
import com.moyang.zero.req.AccountLoginReq;
import com.moyang.zero.req.RegisterReq;
import com.moyang.zero.service.ISysMemberService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.moyang.zero.common.constant.ApplicationConstant.ZERO_API;

/**
 * <p>
 * 墨阳空间 ---平台---成员信息表 前端控制器
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
@RestController
@RequestMapping(ZERO_API +"/sys-member")
@Slf4j
public class SysMemberController extends TemplateController {

	@Resource
	ISysMemberService memberService;

	@GetMapping("/getCheckCode")
	@ApiOperation(value = "墨阳空间-获取验证码")
    Result<String> getCheckCode(@RequestParam("phone") String phone){
    	if(StringUtils.isBlank(phone)){
    		return Result.fail("手机号不能为空");
	    }
    	String checkCode =  memberService.getCheckCode(phone);
    	if (StringUtils.isBlank(checkCode)){
    		return Result.fail();
	    }
    	return Result.success(checkCode);
    }

	@PostMapping("/register")
	@ApiOperation(value = "墨阳空间-新用户注册")
	Result<Boolean> accountRegister(@RequestBody RegisterReq req){
         this.checkRegisterInfo(req);
		 if(memberService.registerNewMember(req)) {
		 	return Result.success();
		 }
		 return Result.fail();
	}


	@PostMapping("/account/login")
	@ApiOperation(value = "墨阳空间-用户登录")
	Result<String> accountLogin(@RequestParam("username") String emy,
	                            @RequestParam("password") String pwd,
	                            @RequestParam(name = "platCode", required = false) String platCode){
		AccountLoginReq req = new AccountLoginReq(emy,pwd,platCode);
		log.info("登录controller：{}", req);
		this.checkAccountLoginInfo(req);
		Result<String> loginResult = memberService.userAccountLogin(req);
		if (loginResult.isSuccess()) {
			String token = JwtUtil.sign(emy, pwd, req.getPlatCode());
			return Result.success(token);
		}
		return loginResult;
	}

	@PostMapping("/info")
	@ApiOperation(value = "墨阳空间-用户登录")
	Result<SysMemberDetail> getSysMemberInfo(){
		return memberService.getSysMemberInfo();
	}

	private void checkAccountLoginInfo(AccountLoginReq req) {
		if (req == null){
			throw new BusinessException("数据异常，登录信息不能为空！");
		}
		if(StringUtils.isBlank(req.getEmy())){
			throw new BusinessException("数据异常，墨阳账号不能为空！");
		}
		if(StringUtils.isBlank(req.getPwd())){
			throw new BusinessException("数据异常，密码不能为空！");
		}
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
