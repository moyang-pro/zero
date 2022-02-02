package com.moyang.zero.entity.auth;

import lombok.Data;

/**
 * @Author: moyang
 * @ClassName: SysPrivilegeDetail
 * @Date: 2022/1/31 15:36
 * @Description: 权限 详细信息
 * @Version: V1.0
 **/
@Data
public class SysPrivilegeDetail {

	/**
	 * 权限编码
	 */
	private String privCode;

	/**
	 * 权限名称
	 */
	private String privName;

	/**
	 * 权限描述
	 */
	private String description;

	/**
	 * 当前权限固定路径（组）
	 */
	private String url;

	/**
	 * 墨阳平台/应用编码
	 */
	private String myCode;
}
