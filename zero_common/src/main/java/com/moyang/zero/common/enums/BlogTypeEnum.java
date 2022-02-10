package com.moyang.zero.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: moyang
 * @ClassName: BlogTypeEnum
 * @Date: 2022/2/11 1:01
 * @Description: 博客类型
 * @Version: V1.0
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum BlogTypeEnum {

	/**
	 * 原创文章 默认值
	 */
	DEFAULT(0,"原创"),
	/**
	 * 转载
	 */
	REPRINT(1,"转载"),

	/**
	 * 翻译
	 */
	TRANSLATION(2, "翻译");

	private Integer code;

	private String label;

	public static boolean isInEnum(int code){

		for (BlogTypeEnum typeEnum : BlogTypeEnum.values()) {
			if (typeEnum.getCode() == code) {
				return true;
			}
		}
		return false;
	}

}
