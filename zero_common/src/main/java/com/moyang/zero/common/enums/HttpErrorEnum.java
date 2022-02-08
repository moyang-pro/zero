package com.moyang.zero.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: moyang
 * @ClassName: HttpErrorEnum
 * @Date: 2022/2/8 21:45
 * @Description: api 请求状态码错误
 * @Version: V1.0
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum HttpErrorEnum {

	/**
	 * token 过期
	 */
	TOKEN_EXPIRED(514,"token已过期"),
	/**
	 *非法token
	 */
	ILL_TOKEN(518,"非法token");

	private  Integer code;

	private  String description;
}
