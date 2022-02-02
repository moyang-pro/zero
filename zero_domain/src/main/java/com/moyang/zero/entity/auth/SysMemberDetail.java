package com.moyang.zero.entity.auth;

import lombok.Data;

import java.util.List;

/**
 * @Author: moyang
 * @ClassName: SysMemberDetail
 * @Date: 2021/8/18 0:13
 * @Description: 用户角色权限完整详细信息
 * @Version: V1.0
 **/
@Data
public class SysMemberDetail{

	/**
	 * 用户emy账号 平台上独一无二
	 */
	private String emy;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 昵称
	 */
	private String nick;

	/**
	 * 头像
	 */
	private String avatar;

	/**
	 * 电话
	 */
	private String telephone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 墨阳空间 唯一账号
	 */
	private String eum;
	/**
	 * 所属平台 编码
	 */
	private String platCode;

	/**
	 * 用户角色列表
	 */
	private List<SysRoleDetail> sysRoleList;


}
