package com.moyang.zero;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;

/**
 * 零度----墨阳空间
 * @date 2021-02-09 11:03
 * @author liuchongyang3
 */
@SpringBootApplication
@MapperScan("com.moyang.zero.mapper")
@PropertySource(value = {
		"classpath:db.properties",
		"classpath:prop/common.properties",
		"classpath:prop/redis/redis.properties",
		"classpath:prop/oss/minio.properties"
}, encoding = "utf-8")
@EnableCaching
@ServletComponentScan
public class ZeroWebApplication {

	/**
	 * @param args 运行参数
	 */
	public static void main(String[] args) {
		SpringApplication.run(ZeroWebApplication.class, args);
	}

}
