package com.moyang.zero.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moyang.zero.common.exception.BusinessException;
import com.moyang.zero.common.util.oss.MinioUtil;
import com.moyang.zero.common.util.thread.ThreadPoolManager;
import com.moyang.zero.dto.LoginInfo;
import com.moyang.zero.entity.ZeroFile;
import com.moyang.zero.mapper.ZeroFileMapper;
import com.moyang.zero.service.IZeroFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 文件上传记录 服务实现类
 * </p>
 *
 * @author moyang
 * @since 2022-02-09
 */
@Service
@Slf4j
public class ZeroFileServiceImpl extends ServiceImpl<ZeroFileMapper, ZeroFile> implements IZeroFileService {


	@Override
	public String uploadBlogImg(MultipartFile file, LoginInfo loginInfo) {
		String relativePath = loginInfo.getEmy();
		if (StringUtils.isBlank(relativePath)) {
			throw  new BusinessException("参数错误： 文件存储相对路径（用户账号）为空");
		}
		String url = MinioUtil.upload(file, relativePath);
		if (!StringUtils.isBlank(url)) {
			log.info("用户" + loginInfo.getEmy() + "成功上传文件 " + url);
			insertFileUploadRecord(file, url, relativePath, "上传博客插图");
		}
		return url;
	}

	@Override
	public String uploadBlogCover(MultipartFile file, LoginInfo loginInfo) {
		String relativePath = loginInfo.getEmy();
		if (StringUtils.isBlank(relativePath)) {
			throw  new BusinessException("参数错误： 文件存储相对路径（用户账号）为空");
		}
		String url = uploadBlogImg(file, loginInfo);
		insertFileUploadRecord(file, url, relativePath, "上传文章封面");
		return url;
	}


	private void insertFileUploadRecord(MultipartFile file, String fileUrl, String emy, String record) {
		// 获取文件名
		String orgName = file.getOriginalFilename();
		if(StringUtils.isBlank(orgName)) {
			orgName = file.getName();
		}
		orgName = MinioUtil.getFileName(orgName);
		//插入数据库
		String finalOrgName = orgName;
		ThreadPoolManager.getInstance().execute(new Runnable() {
			@Override
			public void run() {
                ZeroFile zeroFile = new ZeroFile();
                zeroFile.setFileName(finalOrgName);
                zeroFile.setFileUrl(fileUrl);
                zeroFile.recordCreateInfo(emy, record);
                //插入数据库
                save(zeroFile);
			}
		});
	}
}
