package com.moyang.zero.service.impl;

import com.moyang.zero.common.exception.BusinessException;
import com.moyang.zero.common.util.oss.MinioUtil;
import com.moyang.zero.dto.LoginInfo;
import com.moyang.zero.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: moyang
 * @ClassName: IFileServiceImpl
 * @Date: 2022/2/2 21:34
 * @Description: 文件存储服务
 * @Version: V1.0
 **/
@Service
@Slf4j
public class FileServiceImpl implements IFileService {

	@Override
	public String upload(MultipartFile file, LoginInfo loginInfo) {
		String relativePath = loginInfo.getEmy();
		if (StringUtils.isBlank(relativePath)) {
			throw  new BusinessException("参数错误： 文件存储相对路径（用户账号）为空");
		}
		String url = MinioUtil.upload(file, relativePath);
		if (!StringUtils.isBlank(url)) {
			log.info("用户" + loginInfo.getEmy() + "成功上传文件 " + url);
		}
		return url;
	}
}
