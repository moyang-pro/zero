package com.moyang.zero.service;

import com.moyang.zero.dto.LoginInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: moyang
 * @ClassName: I
 * @Date: 2022/2/2 21:26
 * @Description: IFileService
 * @Version: V1.0
 **/
public interface IFileService {

	/**
	 * 上传文件
	 * @param file 上传的文件
	 * @param loginInfo 用户登录信息
	 * @return 访问链接
	 */
	String upload(MultipartFile file, LoginInfo loginInfo);
}
