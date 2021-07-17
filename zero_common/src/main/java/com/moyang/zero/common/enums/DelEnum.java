package com.moyang.zero.common.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum DelEnum {

	TRUE(0,"未删除"),
	FALSE(1,"已删除");

	private Integer code;
	private String description;
}
