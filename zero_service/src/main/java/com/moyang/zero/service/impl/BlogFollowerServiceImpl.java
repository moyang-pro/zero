package com.moyang.zero.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moyang.zero.common.enums.DelEnum;
import com.moyang.zero.common.util.http.Result;
import com.moyang.zero.entity.BlogFollower;
import com.moyang.zero.mapper.BlogFollowerMapper;
import com.moyang.zero.service.IBlogFollowerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 博客粉丝表 服务实现类
 * </p>
 *
 * @author moyang
 * @since 2022-03-04
 */
@Service
public class BlogFollowerServiceImpl extends ServiceImpl<BlogFollowerMapper, BlogFollower> implements IBlogFollowerService {

	@Resource
	BlogFollowerMapper blogFollowerMapper;

	@Override
	public Result<Boolean> followAuthor(String emy, String author) {
		BlogFollower blogFollower = new LambdaQueryChainWrapper<>(blogFollowerMapper)
				.eq(BlogFollower::getFollower, emy)
				.eq(BlogFollower::getBlogOwner, author)
				.eq(BlogFollower::getDelFlag, DelEnum.valid())
				.one();
		if (blogFollower == null) {
			blogFollower = new BlogFollower();
			blogFollower.setFollower(emy);
			blogFollower.setBlogOwner(author);
			blogFollower.recordCreateInfo(emy);
			this.save(blogFollower);
			return Result.success(true);
		}
		return Result.success(false);
	}

	@Override
	public Result<Boolean> unfollowAuthor(String emy, String author) {
		BlogFollower blogFollower = new LambdaQueryChainWrapper<>(blogFollowerMapper)
				.eq(BlogFollower::getFollower, emy)
				.eq(BlogFollower::getBlogOwner, author)
				.eq(BlogFollower::getDelFlag, DelEnum.valid())
				.one();
		if (blogFollower != null) {
			blogFollower.deleted();
			this.updateById(blogFollower);
			return Result.success(true);
		}
		return Result.success(false);
	}
}
