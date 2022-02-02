package com.moyang.zero.service.auth;


import com.moyang.zero.entity.auth.SysMemberDetail;

/**
 * @Author: moyang
 * @ClassName: ISysMemberDetailService
 * @Date: 2021/8/18 1:02
 * @Description: 用户角色权限接口定义
 * @Version: V1.0
 **/
public  interface ISysMemberDetailService {

	/**
	 * 根据登录用户名获取用户信息
	 * @param emy  username
	 * @param platCode 平台编码
	 * @return 用户信息
	 */
	SysMemberDetail loadUserByUsername(String emy, String platCode);
}
