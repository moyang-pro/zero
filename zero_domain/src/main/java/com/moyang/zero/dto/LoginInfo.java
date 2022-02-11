package com.moyang.zero.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

/**
 * @Author: moyang
 * @ClassName: LoginInfo
 * @Date: 2022/2/2 21:44
 * @Description: 用户登录信息
 * @Version: V1.0
 **/
@Data
@Component
@Lazy
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginInfo {

	private String ip;

	private String emy;

	private String nick;

	private String platCode;

	private LocalDateTime loginTime;

	/**
	 * 来源url
	 */
	private String fromUrl;

}
