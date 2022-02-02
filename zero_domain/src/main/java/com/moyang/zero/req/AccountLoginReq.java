package com.moyang.zero.req;

import com.moyang.zero.common.enums.PlatCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: moyang
 * @ClassName: AccountLoginReq
 * @Date: 2021/8/20 22:31
 * @Description: 用户账号密码登录请求
 * @Version: V1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountLoginReq {

	/**
	 *  描述：墨阳账号
	 */
	private String emy;
	/**
	 *  描述：密码
	 */
	private String pwd;

	/**
	 *  描述：平台编码
	 */
	private String platCode;


	public String getPlatCode() {
		return StringUtils.isBlank(platCode) ? PlatCodeEnum.DEFAULT_CODE.getCode() : platCode;
	}


}
