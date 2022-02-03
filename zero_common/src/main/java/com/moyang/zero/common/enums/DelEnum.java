package com.moyang.zero.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author moyang
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum DelEnum {

	/**
	 * 未删除
	 */
	TRUE(0,"未删除"),
	/**
	 * 已删除
	 */
	FALSE(1,"已删除");

	private Integer code;
	private String description;

	public static int deleted(){
		return DelEnum.FALSE.getCode();
	}
	public static int valid(){
		return DelEnum.TRUE.getCode();
	}
}
