package com.moyang.zero.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moyang.zero.common.util.http.PageRequest;
import com.moyang.zero.common.util.http.PageResult;
import com.moyang.zero.dto.LoginInfo;
import com.moyang.zero.entity.BlogUser;
import com.moyang.zero.entity.SysMember;
import com.moyang.zero.vo.BlogUserVo;

/**
 * <p>
 * 博客系统用户表 服务类
 * </p>
 *
 * @author moyang
 * @since 2022-03-05
 */
public interface IBlogUserService extends IService<BlogUser> {

	/**
	 * 分页查询 博客系统首页 用户排行信息 根据积分等排序
	 * @param pageRequest 分页查询信息
	 * @return 查询结果
	 */
	PageResult<BlogUserVo> getHomePopUserList(PageRequest<String> pageRequest);

	/**
	 * 博客系统新增用户 信息初始化
	 * @param sysMember 墨阳空间用户信息
	 */
	void initBlogUser(SysMember sysMember);

	/**
	 * 查文看章作者信息
	 * @param author 作者账号
	 * @param loginInfo 登录用户信息
	 * @return 作者信息
	 */
	BlogUserVo getBlogAuthorInfo(String author, LoginInfo loginInfo);
}
