package com.moyang.zero.auth.util;

import com.moyang.zero.common.util.http.IpUtil;
import com.moyang.zero.dto.LoginInfo;
import com.moyang.zero.entity.auth.SysMemberDetail;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: moyang
 * @ClassName: LoginContext
 * @Date: 2022/2/2 22:08
 * @Description: 登录用户
 * @Version: V1.0
 **/
@Component
public class LoginContext implements ApplicationContextAware {

	/**
	 * Spring应用上下文环境
	 */
	private static ApplicationContext applicationContext;

	public static LoginInfo getCurrentUser(){
		return applicationContext.getBean(LoginInfo.class);
	}

	public static void setLoginContextByRequest(HttpServletRequest req) {
		LoginInfo loginInfo = getCurrentUser();
		String ip = IpUtil.getIpAddr(req);
		loginInfo.setIp(ip);
	}

	public static void setLoginContextBySysMember(SysMemberDetail sysMemberDetail) {
		LoginInfo loginInfo = getCurrentUser();
		System.out.println("ip............:" + loginInfo.getIp());
		loginInfo.setEmy(sysMemberDetail.getEmy());
		loginInfo.setNick(sysMemberDetail.getNick());
		loginInfo.setPlatCode(sysMemberDetail.getPlatCode());
	}

	@Override
	public void setApplicationContext(@NonNull ApplicationContext context) throws BeansException {
		LoginContext.applicationContext = context;
	}
}
