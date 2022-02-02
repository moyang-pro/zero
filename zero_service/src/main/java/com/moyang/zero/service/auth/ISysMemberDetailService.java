package com.moyang.zero.service.auth;


import com.moyang.zero.entity.SysMember;
import com.moyang.zero.entity.auth.SysMemberDetail;
import com.moyang.zero.entity.auth.SysPrivilegeDetail;
import com.moyang.zero.entity.auth.SysRoleDetail;

import java.util.List;

/**
 * @Author: moyang
 * @ClassName: ISysMemberDetailService
 * @Date: 2021/8/18 1:02
 * @Description: 用户角色权限接口定义
 * @Version: V1.0
 **/
public  interface ISysMemberDetailService {

	/**
	 * 根据登录用户名获取用户简要信息
	 * @param emy  username
	 * @param platCode 平台编码
	 * @return 用户信息
	 */
	SysMember loadUserByUsername(String emy, String platCode);

	/**
	 * 根据登录用户名获取用户角色信息
	 * @param emy  username
	 * @param platCode 平台编码
	 * @return 用户信息
	 */
	List<SysRoleDetail> loadRolesByUser(String emy, String platCode);

	/**
	 * 根据登录用户名获取用户所有权限信息
	 * @param emy  username
	 * @param platCode 平台编码
	 * @return 用户信息
	 */
	List<SysPrivilegeDetail> loadPrivByUser(String emy, String platCode);

	/**
	 * 根据登录用户名获取用户所有信息
	 * @param emy  username
	 * @param platCode 平台编码
	 * @return 用户信息
	 */
	SysMemberDetail loadAllInfoByUser(String emy, String platCode);
}
