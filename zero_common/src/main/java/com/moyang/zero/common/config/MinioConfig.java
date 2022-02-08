package com.moyang.zero.common.config;

import com.moyang.zero.common.constant.SymbolConstant;
import com.moyang.zero.common.util.oss.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.moyang.zero.common.constant.ApplicationConstant.HTTP;
import static com.moyang.zero.common.constant.ApplicationConstant.HTTP_PRE;

/**
 * @Author: moyang
 * @ClassName: MinioConfig
 * @Date: 2022/2/2 18:36
 * @Description: minio 存储
 * @Version: V1.0
 **/
@Slf4j
@Configuration
public class MinioConfig {

	/**
	 * url
	 */
	@Value(value = "${minio.url}")
	private String minioUrl;

	/**
	 * username
	 */
	@Value(value = "${minio.access-key}")
	private String minioName;

	/**
	 * password
	 */
	@Value(value = "${minio.secret-key}")
	private String minioPass;

	@Value(value = "${minio.bucket}")
	private String bucketName;

	@Bean
	public void initMinio(){
		log.info("initMinio start ...");
		if(!minioUrl.startsWith(HTTP)){
			minioUrl = HTTP_PRE + minioUrl;
		}
		if(!minioUrl.endsWith(SymbolConstant.SLASH)){
			minioUrl = minioUrl.concat(SymbolConstant.SLASH);
		}
		MinioUtil.setMinioUrl(minioUrl);
		MinioUtil.setMinioName(minioName);
		MinioUtil.setMinioPass(minioPass);
		MinioUtil.setBucketName(bucketName);
	}

}
