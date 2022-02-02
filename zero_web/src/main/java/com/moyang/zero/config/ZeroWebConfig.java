package com.moyang.zero.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

/**
 * @Author: moyang
 * @ClassName: ZeroWebConfig
 * @Date: 2022/2/3 0:11
 * @Description: 自定义web应用设置
 * @Version: V1.0
 **/
@Configuration
public class ZeroWebConfig extends RequestContextListener {

	public ZeroWebConfig() {
		super();
	}
}
