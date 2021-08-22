package com.moyang.zero.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author: moyang
 * @ClassName: PlatCodeEnum
 * @Date: 2021/8/22 23:49
 * @Description: 平台编码枚举值
 * @Version: V1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum PlatCodeEnum {
	/**
	 * 默认平台编码
	 */
	DEFAULT_CODE("MOYANG_CODE","默认平台编码"),
    /**
	 * ZERO平台编码
	 */
    ZERO_CODE("ZERO_CODE","ZERO平台编码");

	private  String code;

	private  String description;
}
