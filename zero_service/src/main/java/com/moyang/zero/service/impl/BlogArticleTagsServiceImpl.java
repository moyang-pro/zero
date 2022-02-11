package com.moyang.zero.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moyang.zero.common.enums.DelEnum;
import com.moyang.zero.common.exception.BusinessException;
import com.moyang.zero.entity.BlogArticle;
import com.moyang.zero.entity.BlogArticleTags;
import com.moyang.zero.mapper.BlogArticleTagsMapper;
import com.moyang.zero.service.IBlogArticleTagsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 博客文章标签 服务实现类
 * </p>
 *
 * @author moyang
 * @since 2022-02-09
 */
@Service
public class BlogArticleTagsServiceImpl extends ServiceImpl<BlogArticleTagsMapper, BlogArticleTags> implements IBlogArticleTagsService {

	@Resource
	BlogArticleTagsMapper tagsMapper;

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public void saveOrUpdateTags(List<String> tags, BlogArticle blogArticle) {

		if (CollectionUtils.isEmpty(tags)) {
			throw new BusinessException("文章标签为空");
		}
		List<BlogArticleTags> articleTags = new LambdaQueryChainWrapper<>(tagsMapper).eq(BlogArticleTags::getArticleId, blogArticle.getId())
				.eq(BlogArticleTags::getDelFlag, DelEnum.valid()).list();
		List<String> oldTags = articleTags.stream().map(BlogArticleTags::getArticleTag).distinct().collect(Collectors.toList());

		// 删除旧的
		if (!CollectionUtils.isEmpty(articleTags)) {
			articleTags.stream().filter(item -> !tags.contains(item.getArticleTag())).forEach(item -> {
						item.setDelFlag(DelEnum.deleted());
						item.recordUpdateInfo(blogArticle.getAuthor());
			});
			updateBatchById(articleTags);
		}
        // 插入新的
		List<String> newTags = tags.stream().filter(item -> !oldTags.contains(item)).collect(Collectors.toList());
		if (CollectionUtils.isEmpty(newTags)) {
			return;
		}
		List<BlogArticleTags> articleNewTags = new ArrayList<>(newTags.size());
		for (String tag : newTags) {
			BlogArticleTags newTag = new BlogArticleTags();
			newTag.recordCreateInfo(blogArticle.getAuthor());
			newTag.setArticleId(blogArticle.getId());
			newTag.setArticleTag(tag);
			articleNewTags.add(newTag);
		}
		saveBatch(articleNewTags);
	}
}
