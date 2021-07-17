package com.moyang.zero;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;

/**
 * 零度----墨阳空间
 * @date 2021-02-09 11:03
 * @author liuchongyang3
 */
@SpringBootApplication
@MapperScan("com.moyang.zero.mapper")
@PropertySource("classpath:db.properties")
@EnableCaching
//@ImportResource("classpath:/redis/redis.xml")//加载自定义XML配置文件位置
public class ZeroWebApplication {

	/**
	 * @param args 运行参数
	 */
	public static void main(String[] args) {
		SpringApplication.run(ZeroWebApplication.class, args);
	}

}
