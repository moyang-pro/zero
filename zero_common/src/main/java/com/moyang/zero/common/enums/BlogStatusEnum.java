package com.moyang.zero.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: moyang
 * @ClassName: BlogStatusEnum
 * @Date: 2022/2/11 20:22
 * @Description: 文章状态
 * @Version: V1.0
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum BlogStatusEnum {

	/**
	 * 草稿 默认值
	 */
	DEFAULT(0,"草稿"),
	/**
	 * 发布
	 */
	PUBLISH(1,"发布"),

	/**
	 * 开放状态,审核通过
	 */
	PUBLISHED(2, "开放状态"),

	/**
	 * 锁定
	 */
	LOCKED(3,"锁定");

	private Integer code;

	private String label;

	public static boolean isInEnum(int code){

		for (BlogStatusEnum typeEnum : BlogStatusEnum.values()) {
			if (typeEnum.getCode() == code) {
				return true;
			}
		}
		return false;
	}
}
