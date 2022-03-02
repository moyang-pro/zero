package com.moyang.zero.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moyang.zero.bo.BlogArticleBo;
import com.moyang.zero.common.enums.BlogPublishTypeEnum;
import com.moyang.zero.common.enums.BlogStatusEnum;
import com.moyang.zero.common.enums.DelEnum;
import com.moyang.zero.common.exception.BusinessException;
import com.moyang.zero.common.util.http.PageRequest;
import com.moyang.zero.common.util.http.PageResult;
import com.moyang.zero.dto.LoginInfo;
import com.moyang.zero.entity.BlogArticle;
import com.moyang.zero.entity.BlogArticleTags;
import com.moyang.zero.mapper.BlogArticleMapper;
import com.moyang.zero.mapper.BlogArticleTagsMapper;
import com.moyang.zero.pojo.blog.BlogSelectParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: moyang
 * @ClassName: BlogArticleManager
 * @Date: 2022/2/4 1:53
 * @Description: 博客文章信息管理
 * @Version: V1.0
 **/
@Component
@Slf4j
public class BlogArticleManager {

	@Resource
	BlogArticleMapper blogArticleMapper;

	@Resource
	BlogArticleTagsMapper tagsMapper;


	public boolean checkBlogArticleAuth(BlogArticleBo blogArticleBo, LoginInfo loginInfo) {
		// 一定是登录用户
		if(loginInfo.getEmy().equals(blogArticleBo.getAuthor())){
			return true;
		}
		// 私密 一定不可见
		if (BlogPublishTypeEnum.MYSELF.getCode().equals(blogArticleBo.getPublishType())) {
			return false;
		}
		// 如果文章是上锁或者草稿状态一定不可见
		if (BlogStatusEnum.DEFAULT.getCode().equals(blogArticleBo.getArticleStatus())
				|| BlogStatusEnum.LOCKED.getCode().equals(blogArticleBo.getArticleStatus())) {
			return false;
		}
		// 非上锁或者草稿状态文章 公开 一定可见
		if (BlogPublishTypeEnum.DEFAULT.getCode().equals(blogArticleBo.getPublishType())) {
			return true;
		}
		// 如果是粉丝一定可见

		// 不是粉丝，非开放状态，不可见
		return  false;
	}


	public BlogArticle getBlogArticleById(Long blogId){
		if (blogId == null || blogId < 0){
			return null;
		}
		return new LambdaQueryChainWrapper<>(blogArticleMapper)
				.eq(BlogArticle::getId, blogId).eq(BlogArticle::getDelFlag, DelEnum.valid())
				.one();
	}

	public BlogArticle getMyBlogArticle(Long blogId, String author){
		if (blogId == null || blogId < 0){
			return null;
		}
		if (StringUtils.isBlank(author)) {
			return null;
		}
		return new LambdaQueryChainWrapper<>(blogArticleMapper)
				.eq(BlogArticle::getId, blogId).eq(BlogArticle::getAuthor, author)
				.eq(BlogArticle::getDelFlag, DelEnum.valid())
				.one();
	}

	/**
	 * 获取作者的全部文章
	 * @param pageRequest 作者账号,分页信息
	 * @return 博客信息
	 */
	public PageResult<BlogArticle> getBlogListByAuthor(PageRequest<String> pageRequest) {
		String author = pageRequest.getData();
		if (StringUtils.isBlank(author)) {
			return PageResult.emptyList();
		}
		Page<BlogArticle> page = new Page<>(pageRequest.getPageIndex(), pageRequest.getPageSize());

		IPage<BlogArticle> articlePage = new LambdaQueryChainWrapper<>(blogArticleMapper)
				.eq(BlogArticle::getAuthor, author).eq(BlogArticle::getDelFlag, DelEnum.valid()).page(page);

		return PageResult.success(articlePage.getRecords(), articlePage.getTotal());
	}


	public BlogArticleBo getBlogAllInfoById(Long blogId){
		if (blogId == null || blogId < 0){
			return null;
		}
		BlogArticleBo blogArticleBo = blogArticleMapper.selectBlogAllInfoById(blogId, null);
		if (blogArticleBo == null) {
			throw  new BusinessException("无效的博客文章ID！");
		}

		return  blogArticleBo;
	}


	public BlogArticleBo getMyBlogAllInfo(Long blogId, String author){
		if (blogId == null || blogId < 0){
			return null;
		}
		if (StringUtils.isBlank(author)) {
			return null;
		}
		BlogArticleBo blogArticleBo = blogArticleMapper.selectBlogAllInfoById(blogId, author);
		if (blogArticleBo == null) {
			throw  new BusinessException("无效的博客文章ID！");
		}
		return  blogArticleBo;
	}


	/**
	 * 获取作者的全部文章
	 * @param pageIndex 当前页
	 * @param pageSize 每页数量
	 * @param param 查询参数 作者账号
	 * @return 博客信息
	 */
	public PageResult<BlogArticleBo> getBlogAllInfoListByAuthor(int pageIndex, int pageSize, BlogSelectParam param) {
		String author = param.getAuthor();
		if (StringUtils.isBlank(author)) {
			return PageResult.emptyList();
		}
		Page<BlogArticleBo> page = new Page<>(pageIndex, pageSize);
		IPage<BlogArticleBo> articlePage = blogArticleMapper.selectBlogAllInfoByPage(page, param);
		return PageResult.success(articlePage.getRecords(), articlePage.getTotal());
	}


	public List<String> getBlogArticleTags(Long blogId){
		if (blogId == null || blogId < 0){
			return null;
		}

		return new LambdaQueryChainWrapper<>(tagsMapper)
				.eq(BlogArticleTags::getArticleId, blogId)
				.eq(BlogArticleTags::getDelFlag, DelEnum.valid())
				.list().stream().map(BlogArticleTags::getArticleTag).collect(Collectors.toList());
	}


	/**
	 * 获取作者的全部文章
	 * @param pageIndex 当前页
	 * @param pageSize 每页数量
	 * @param param 排序字段
	 * @return 博客信息
	 */
	public PageResult<BlogArticleBo> getHomeBlogListByTab(int pageIndex, int pageSize, BlogSelectParam param) {
		Page<BlogArticleBo> page = new Page<>(pageIndex, pageSize);
		IPage<BlogArticleBo> articlePage = blogArticleMapper.selectBlogAllInfoByPage(page, param);
		return PageResult.success(articlePage.getRecords(), articlePage.getTotal());
	}


	public List<BlogArticle> getHomeBlogTop3(){
		return new LambdaQueryChainWrapper<>(blogArticleMapper)
				.isNotNull(BlogArticle::getCoverUrl).eq(BlogArticle::getDelFlag, DelEnum.valid())
				.orderByDesc(BlogArticle::getPublishTime).apply("limit {0}", 3)
				.list();
	}
}
