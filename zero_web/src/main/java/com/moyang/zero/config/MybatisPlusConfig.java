package com.moyang.zero.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: moyang
 * @ClassName: MybatisPlusConfig
 * @Date: 2022/2/6 16:45
 * @Description: mybatis plus 分页插件配置
 * @Version: V1.0
 **/
@Configuration
public class MybatisPlusConfig {
	/**
	 * mybatis-plus分页插件
	 */
	@Bean
	public MybatisPlusInterceptor MybatisPlusInterceptor() {
		MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
		//乐观锁
		mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
		//分页配置
		mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
		return mybatisPlusInterceptor;
	}
}
