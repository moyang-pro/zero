package com.moyang.zero.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moyang.zero.dto.LoginInfo;
import com.moyang.zero.entity.ZeroFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 文件上传记录 服务类
 * </p>
 *
 * @author moyang
 * @since 2022-02-09
 */
public interface IZeroFileService extends IService<ZeroFile> {

	/**
	 * 上传博客插图
	 * @param file 上传的文件
	 * @param loginInfo 用户登录信息
	 * @return 访问链接
	 */
	String uploadBlogImg(MultipartFile file, LoginInfo loginInfo);

	/**
	 * 上传博客封面
	 * @param file 上传的文件
	 * @param loginInfo 用户登录信息
	 * @return 访问链接
	 */
	String uploadBlogCover(MultipartFile file, LoginInfo loginInfo);

}
