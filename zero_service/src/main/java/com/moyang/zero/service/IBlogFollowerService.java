package com.moyang.zero.service;

import com.moyang.zero.common.util.http.Result;
import com.moyang.zero.entity.BlogFollower;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 博客粉丝表 服务类
 * </p>
 *
 * @author moyang
 * @since 2022-03-04
 */
public interface IBlogFollowerService extends IService<BlogFollower> {

	/**
	 * 关注作者
	 * @param emy 粉丝账号
	 * @param author 作者账号
	 * @return 关注成功与否
	 */
	Result<Boolean> followAuthor(String emy, String author);

	/**
	 * 取消关注作者
	 * @param emy 粉丝账号
	 * @param author 作者账号
	 * @return 取消关注成功与否
	 */
	Result<Boolean> unfollowAuthor(String emy, String author);
}
