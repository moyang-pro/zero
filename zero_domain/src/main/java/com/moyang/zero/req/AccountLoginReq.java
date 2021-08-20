package com.moyang.zero.req;

import lombok.Data;

/**
 * @Author: moyang
 * @ClassName: AccountLoginReq
 * @Date: 2021/8/20 22:31
 * @Description: 用户账号密码登录请求
 * @Version: V1.0
 **/
@Data
public class AccountLoginReq {

	/**
	 *  描述：墨阳账号
	 */
	private String emy;
	/**
	 *  描述：密码
	 */
	private String pwd;


}
