package com.moyang.zero.req;

import lombok.Data;

/**
 * @author moyang
 */
@Data
public class RegisterReq {
	/**
	*  描述：墨阳账号
	*/
	private String emy;
	/**
	*  描述：密码
	*/
	private String pwd;
	/**
	*  描述：手机号
	*/
	private String phone;

	/**
	*  描述：验证码
	*/
	private String checkCode;

	/**
	*  描述：平台信息
	*/
	private String platCode;

}
