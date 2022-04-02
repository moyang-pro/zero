package com.moyang.zero.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moyang.zero.common.enums.DelEnum;
import com.moyang.zero.common.exception.BusinessException;
import com.moyang.zero.common.util.MyBeanCopier;
import com.moyang.zero.common.util.http.PageRequest;
import com.moyang.zero.common.util.http.PageResult;
import com.moyang.zero.dto.LoginInfo;
import com.moyang.zero.entity.BlogUser;
import com.moyang.zero.entity.SysMember;
import com.moyang.zero.manager.BlogUserManager;
import com.moyang.zero.mapper.BlogUserMapper;
import com.moyang.zero.service.IBlogUserService;
import com.moyang.zero.vo.BlogUserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 博客系统用户表 服务实现类
 * </p>
 *
 * @author moyang
 * @since 2022-03-05
 */
@Service
@Slf4j
public class BlogUserServiceImpl extends ServiceImpl<BlogUserMapper, BlogUser> implements IBlogUserService {

	@Resource
	BlogUserManager blogUserManager;

	@Resource
	BlogUserMapper blogUserMapper;

	@Override
	public PageResult<BlogUserVo> getHomePopUserList(PageRequest<String> pageRequest) {
		Page<BlogUser> page = new Page<>(pageRequest.getPageIndex(), pageRequest.getPageSize());
		IPage<BlogUser> userPage = new LambdaQueryChainWrapper<>(blogUserMapper)
				.eq(BlogUser::getDelFlag, DelEnum.valid())
				.orderByDesc(BlogUser::getPoint)
				.orderByDesc(BlogUser::getArticleCount)
				.orderByDesc(BlogUser::getFollowedCount)
				.orderByDesc(BlogUser::getFaqCount)
				.page(page);
		List<BlogUser> blogUserList = page.getRecords();
		if (userPage.getTotal() == 0 || CollectionUtils.isEmpty(blogUserList)){
			return PageResult.emptyList();
		}
		List<BlogUserVo> blogUserVoList = MyBeanCopier.copyList(blogUserList, BlogUserVo::new);
		return PageResult.success(blogUserVoList, userPage.getTotal());
	}

	@Override
	public void initBlogUser(SysMember sysMember) {
		BlogUser blogUser = new LambdaQueryChainWrapper<>(blogUserMapper)
				.eq(BlogUser::getDelFlag, DelEnum.valid())
				.eq(BlogUser::getBlogUser, sysMember.getEmy()).one();
		if (blogUser != null){
			log.error("blogUser 已经存在！");
			return;
		}
		BlogUser newUser = new BlogUser();
		newUser.setBlogUser(sysMember.getEmy());
		newUser.setAvatar(sysMember.getAvatar());
		newUser.setNickName(sysMember.getNick());
		newUser.recordCreateInfo(sysMember.getEmy());
		this.save(newUser);
	}

	@Override
	public BlogUserVo getBlogAuthorInfo(String author, LoginInfo loginInfo) {
		if (StringUtils.isBlank(author)) {
			throw new BusinessException("作者账号为空！");
		}
		BlogUser blogAuthorInfo = blogUserManager.getBlogAuthorInfo(author);
		if (blogAuthorInfo == null){
			throw new BusinessException("作者信息为空！");
		}
		BlogUserVo blogUserVo =  MyBeanCopier.copyBean(blogAuthorInfo, BlogUserVo::new);
		blogUserVo.setHasFollowed(blogUserManager.hasFollowBlogOwner(loginInfo.getEmy(), author));
		return blogUserVo;
	}
}
