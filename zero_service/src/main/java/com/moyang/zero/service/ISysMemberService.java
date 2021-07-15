package com.moyang.zero.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.moyang.zero.entity.SysMember;
import com.baomidou.mybatisplus.extension.service.IService;
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
}
