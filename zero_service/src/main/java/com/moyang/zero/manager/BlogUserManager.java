package com.moyang.zero.manager;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.moyang.zero.common.enums.DelEnum;
import com.moyang.zero.entity.*;
import com.moyang.zero.mapper.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

/**
 * @Author: moyang
 * @ClassName: BlogUserManager
 * @Date: 2022/3/30 23:13
 * @Description: 博客作者信息管理类
 * @Version: V1.0
 **/
@Component
public class BlogUserManager {

	@Resource
	BlogUserMapper blogUserMapper;

	@Resource
	BlogArticleReadRecordMapper readRecordMapper;

	@Resource
	BlogFollowerMapper blogFollowerMapper;

	@Resource
	BlogArticleLikeMapper articleLikeMapper;

	@Resource
	BlogArticleCollectionMapper articleCollectionMapper;

	public BlogUser getBlogAuthorInfo(String emy){
		if (StringUtils.isBlank(emy)) {
			return null;
		}
		return new LambdaQueryChainWrapper<>(blogUserMapper)
				.eq(BlogUser::getDelFlag, DelEnum.valid())
				.eq(BlogUser::getBlogUser, emy).one();
	}

	/**
	 * 是否已经阅读过这篇文章
	 * @param reader 读者
	 * @param blogId 文章ID
	 * @return 是否
	 */
	public boolean hasReadThisArticle(String reader, Long blogId) {
		return !CollectionUtils.isEmpty(new LambdaQueryChainWrapper<>(readRecordMapper)
				.eq(BlogArticleReadRecord::getEmy, reader).eq(BlogArticleReadRecord::getArticleId, blogId)
				.eq(BlogArticleReadRecord::getDelFlag, DelEnum.valid()).list());
	}

	/**
	 * 是否已经关注这篇文章作者
	 * @param reader 读者
	 * @param author 作者
	 * @return 是否
	 */
	public boolean hasFollowBlogOwner(String reader, String author) {
		return new LambdaQueryChainWrapper<>(blogFollowerMapper)
				.eq(BlogFollower::getBlogOwner, author).eq(BlogFollower::getFollower, reader)
				.eq(BlogFollower::getDelFlag, DelEnum.valid()).one() != null;
	}

	/**
	 * 是否已经点赞这篇文章
	 * @param reader 读者
	 * @param blogId 文章ID
	 * @return 是否
	 */
	public boolean hasLikeBlogArticle(String reader, Long blogId) {
		return new LambdaQueryChainWrapper<>(articleLikeMapper)
				.eq(BlogArticleLike::getBlogOwner, blogId).eq(BlogArticleLike::getArticleId, blogId)
				.eq(BlogArticleLike::getDelFlag, DelEnum.valid()).one() != null;
	}

	/**
	 * 是否已经收藏这篇文章
	 * @param reader 读者
	 * @param blogId 文章ID
	 * @return 是否
	 */
	public boolean hasCollectBlogArticle(String reader, Long blogId) {
		return new LambdaQueryChainWrapper<>(articleCollectionMapper)
				.eq(BlogArticleCollection::getBlogOwner, blogId).eq(BlogArticleCollection::getArticleId, blogId)
				.eq(BlogArticleCollection::getDelFlag, DelEnum.valid()).one() != null;
	}
}
