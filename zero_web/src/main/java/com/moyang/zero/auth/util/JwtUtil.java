package com.moyang.zero.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.moyang.zero.common.enums.PlatCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * @Author: moyang
 * @ClassName: JWTUtil
 * @Date: 2021/9/16 23:15
 * @Description: JWT 工具类
 * @Version: V1.0
 **/
@Slf4j
public class JwtUtil {

	/**过期时间24小时*/
	private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;

	public static boolean verify(String token, String username, String platCode,  String secret) {
		try {
			Algorithm algorithm = Algorithm.HMAC512(secret);

			JWTVerifier verifier = JWT.require(algorithm)
					.withClaim("username", username)
					.withClaim("platCode", platCode)
					.build();
			verifier.verify(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @Title: getUsername
	 * @Description: 获取token中的信息无需secret解密也能获得
	 * @author: MoYang
	 * @create: 2020-07-05 16:40
	 * @param token token
	 */
	public static String getUsername(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			System.out.println(jwt.getId());
			return jwt.getId();
		} catch (JWTDecodeException e) {
			return null;
		}
	}

	/**
	 * @Title: getPlatCode
	 * @Description: 获取token中的信息无需secret解密也能获得
	 * @author: MoYang
	 * @create: 2020-07-05 16:40
	 * @param token token
	 */
	public static String getPlatCode(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			String platCode = jwt.getClaim("platCode").asString();
			return StringUtils.isBlank(platCode) ? PlatCodeEnum.DEFAULT_CODE.getCode() : platCode;
		} catch (JWTDecodeException e) {
			return null;
		}
	}

	public static String sign(String username, String platCode, String secret){
		Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
		Algorithm algorithm = Algorithm.HMAC512(secret);

		// 附带username platCode信息
		return JWT.create().withJWTId(username)
				.withClaim("username", username)
				.withClaim("platCode", platCode)
				.withExpiresAt(date)
				.sign(algorithm);
	}
}
