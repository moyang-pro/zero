package com.moyang.zero.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: moyang
 * @ClassName: AppCodeEnum
 * @Date: 2022/3/3 23:39
 * @Description: 应用编码
 * @Version: V1.0
 **/
@AllArgsConstructor
@Getter
public enum AppCodeEnum {

	/**
	 * ZERO平台编码
	 */
	BLOG_CODE("ZERO_BLOG","博客系统编码");

	private  String code;

	private  String description;
}
