package com.moyang.zero.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moyang.zero.common.util.http.Result;
import com.moyang.zero.entity.SysMember;
import com.moyang.zero.entity.auth.SysMemberDetail;
import com.moyang.zero.req.AccountLoginReq;
import com.moyang.zero.req.RegisterReq;

/**
 * <p>
 * 墨阳空间 ---平台---成员信息表 服务类
 * </p>
 *
 * @author moyang
 * @since 2021-02-08
 */
public interface ISysMemberService extends IService<SysMember> {

	/**
	 * 注册新用户
	 * @param req 注册请求参数
	 * @return 处理结果
	 */
	boolean registerNewMember(RegisterReq req);

	/**
	 * 获取验证码
	 * @param phone 手机号
	 * @return 6位验证码
	 */
	String getCheckCode(String phone);

	/**
	 * 用户账号密码登录
	 * @param req 登录信息
	 * @return 登录成功与失败
	 */
	Result<String> userAccountLogin(AccountLoginReq req);

	/**
	 * 获取用户角色权限全量信息
	 * @param emy  username
	 * @param platCode 平台编码
	 * @return 用户角色权限全量信息
	 */
	SysMemberDetail getSysMemberInfo(String emy, String platCode);
}
