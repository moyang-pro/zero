package com.moyang.zero.auth.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: moyang
 * @ClassName: JwtToken
 * @Date: 2022/1/31 15:17
 * @Description: 自定义 jwt token
 * @Version: V1.0
 **/
public class JwtToken implements AuthenticationToken {
	private static final long serialVersionUID = 1L;

	/**
	 * 秘钥
	 */
	private final String token;

	public JwtToken(String token) {
		this.token = token;
	}

	@Override
	public Object getPrincipal() {
		return token;
	}


	@Override
	public Object getCredentials() {
		return token;
	}
}
