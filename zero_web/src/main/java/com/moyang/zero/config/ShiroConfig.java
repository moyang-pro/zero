package com.moyang.zero.config;

import com.moyang.zero.auth.filter.JwtFilter;
import com.moyang.zero.auth.realm.MyRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.moyang.zero.common.constant.ApplicationConstant.ZERO_API;

/**
 * @Author: moyang
 * @ClassName: ShiroConfig
 * @Date: 2022/1/31 15:10
 * @Description: shiro config
 * @Version: V1.0
 **/
@Configuration
@Slf4j
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
		log.info("ShiroFilterFactoryBean config init start ...");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 添加自己的过滤器并且取名为jwt
		Map<String, Filter> filterMap = new HashMap<>(1);
		filterMap.put("jwt", new JwtFilter());
		shiroFilterFactoryBean.setFilters(filterMap);
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		shiroFilterFactoryBean.setUnauthorizedUrl(ZERO_API + "/sys-member/account/login");

		//自定义url规则
		Map<String, String> filterRuleMap = new LinkedHashMap<String, String>();
		// 访问401和404页面不通过我们的Filter
		filterRuleMap.put(ZERO_API + "/401", "anon");
		filterRuleMap.put(ZERO_API + "/404", "anon");
		filterRuleMap.put(ZERO_API + "/sys-member/account/login", "anon");
		filterRuleMap.put(ZERO_API + "/sys-member/register", "anon");
		filterRuleMap.put(ZERO_API + "/sys-member/getCheckCode", "anon");
		filterRuleMap.put(ZERO_API + "/*/page/**", "anon");

		// 所有的请求通过我们自己的JWT filter
		filterRuleMap.put("/**", "jwt");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterRuleMap);
		return shiroFilterFactoryBean;
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultSecurityManager(@Qualifier("myRealm") MyRealm myRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myRealm);
		return securityManager;
	}

	@Bean(name="myRealm")
	public MyRealm getMyRealm() {
		return new MyRealm();
	}

	/**
	 * 下面的代码是添加注解支持
	 */
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		// 强制使用cglib，防止重复代理和可能引起代理出错的问题
		// https://zhuanlan.zhihu.com/p/29161098
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
}
