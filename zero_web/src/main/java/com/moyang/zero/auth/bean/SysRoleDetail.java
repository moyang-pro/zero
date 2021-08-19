package com.moyang.zero.auth.bean;

import com.moyang.zero.entity.SysPrivilege;
import lombok.Data;

import java.util.List;

/**
 * @Author: moyang
 * @ClassName: SysRoleDetail
 * @Date: 2021/8/18 0:21
 * @Description: 角色权限信息
 * @Version: V1.0
 **/
@Data
public class SysRoleDetail {
	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 角色编码 MYSR /MYPR
	 */
	private String roleCode;

	/**
	 * 描述信息
	 */
	private String description;

	/**
	 * 角色类型：空间角色 平台角色 应用角色
	 */
	private String type;

	/**
	 * 墨阳编码 查看my_code表
	 */
	private String myCode;

	/**
	 * 角色权限列表
	 */
	private List<SysPrivilege> sysPrivilegeList;
}
