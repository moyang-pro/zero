package com.moyang.zero.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: moyang
 * @ClassName: BlogPublishTypeEnum
 * @Date: 2022/2/11 1:23
 * @Description: 博客发布类型
 * @Version: V1.0
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum BlogPublishTypeEnum {

	/**
	 * 公开，所有人可见
	 */
	DEFAULT(0,"公开"),
	/**
	 * 私密
	 */
	REPRINT(1,"私密"),

	/**
	 * 仅粉丝可见
	 */
	TRANSLATION(2, "仅粉丝可见");

	private Integer code;

	private String label;

	public static boolean isInEnum(int code){

		for (BlogPublishTypeEnum typeEnum : BlogPublishTypeEnum.values()) {
			if (typeEnum.getCode() == code) {
				return true;
			}
		}
		return false;
	}
}
