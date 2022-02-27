package com.moyang.zero.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: moyang
 * @ClassName: BlogHomeTabEnum
 * @Date: 2022/2/27 14:24
 * @Description: 博客系统首页 TAB页
 * @Version: V1.0
 **/
@AllArgsConstructor
@Getter
public enum BlogHomeTabEnum {

	/**
	 * 最新 默认值
	 */
	LAST("LAST","最新"),

	/**
	 * 最热
	 */
	POP("POP","最热");

	private String code;

	private String label;
}
