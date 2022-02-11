package com.moyang.zero.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moyang.zero.common.enums.BlogPublishTypeEnum;
import com.moyang.zero.common.enums.BlogStatusEnum;
import com.moyang.zero.common.enums.BlogTypeEnum;
import com.moyang.zero.common.enums.DelEnum;
import com.moyang.zero.common.util.MyBeanCopier;
import com.moyang.zero.common.util.http.PageRequest;
import com.moyang.zero.common.util.http.PageResult;
import com.moyang.zero.common.util.http.Result;
import com.moyang.zero.dto.LoginInfo;
import com.moyang.zero.entity.BlogArticle;
import com.moyang.zero.entity.BlogArticleReadRecord;
import com.moyang.zero.manager.BlogArticleManager;
import com.moyang.zero.mapper.BlogArticleMapper;
import com.moyang.zero.req.BlogPublishReq;
import com.moyang.zero.req.BlogSaveReq;
import com.moyang.zero.service.IBlogArticleReadRecordService;
import com.moyang.zero.service.IBlogArticleService;
import com.moyang.zero.service.IBlogArticleTagsService;
import com.moyang.zero.vo.BlogArticleVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author moyang
 * @since 2022-02-02
 */
@Service
public class BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements IBlogArticleService {

	@Resource
	private BlogArticleManager blogArticleManager;

	/**
	 * 注意不要相互注入
	 */
	@Resource
	private IBlogArticleTagsService tagsService;


	@Resource
	private IBlogArticleReadRecordService readRecordService;

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public Result<Long> saveBlog(BlogSaveReq blog) {
		BlogArticle blogArticle = new BlogArticle();
		blogArticle.setArticleTitle(blog.getTitle());
		blogArticle.setArticleDes(blog.getDes());
		blogArticle.setArticleContent(blog.getHtmlContent());
		blogArticle.setArticleText(blog.getTextContent());
		blogArticle.setAuthor(blog.getAuthor());
		blogArticle.setArticleStatus(BlogStatusEnum.DEFAULT.getCode());
		blogArticle.recordCreateInfo(blog.getAuthor(), "保存新文章");
		this.save(blogArticle);
		return Result.success(blogArticle.getId());
	}

	@Override
	public Result<BlogArticleVo> getBlogNoAuth(Long blogId) {
		BlogArticle blogArticle = blogArticleManager.getBlogArticleById(blogId);
		if (blogArticle == null) {
			return Result.fail("无效的博客文章ID！");
		}
		if (!BlogStatusEnum.PUBLISHED.getCode().equals(blogArticle.getArticleStatus())){
			return Result.fail("博客文章暂未开放！");
		}
		if (!BlogPublishTypeEnum.DEFAULT.getCode().equals(blogArticle.getPublishType())){
			return Result.fail("博客文章您暂无权限查看！");
		}
		BlogArticleVo blogArticleVo = getBlogArticleVo(blogArticle);
		List<String> tags = blogArticleManager.getBlogArticleTags(blogId);
		blogArticleVo.setTags(tags);
		return Result.success(blogArticleVo);
	}

	@Override
	public Result<BlogArticleVo> getBlogOfAuthor(Long blogId, String author) {
		BlogArticle blogArticle = blogArticleManager.getMyBlogArticle(blogId, author);
		if (blogArticle == null) {
			return Result.fail("无效的博客文章ID！");
		}
		BlogArticleVo blogArticleVo = getBlogArticleVo(blogArticle);
		List<String> tags = blogArticleManager.getBlogArticleTags(blogId);
		blogArticleVo.setTags(tags);
		return Result.success(blogArticleVo);
	}

	private BlogArticleVo getBlogArticleVo(BlogArticle blogArticle) {
		BlogArticleVo blogArticleVo = MyBeanCopier.copyBean(blogArticle, BlogArticleVo::new);
		blogArticleVo.setTitle(blogArticle.getArticleTitle());
		blogArticleVo.setAuthor(blogArticle.getAuthor());
		blogArticleVo.setDes(blogArticle.getArticleDes());
		blogArticleVo.setHtmlContent(blogArticle.getArticleContent());
		blogArticleVo.setTextContent(blogArticle.getArticleText());
		return blogArticleVo;
	}

	@Override
	public Result<BlogArticleVo> readBlogArticle(Long blogId, LoginInfo loginInfo){
		BlogArticle blogArticle = blogArticleManager.getBlogArticleById(blogId);
		// 校验权限 + 记录浏览
		boolean auth = blogArticleManager.checkBlogArticleAuth(blogArticle, loginInfo);
		if (!auth) {
			return Result.fail(401,"无权限查看该文章");
		}
		Result<BlogArticleVo> result = getBlogNoAuth(blogId);
		if (result.isSuccess()) {
			BlogArticleReadRecord readRecord = new BlogArticleReadRecord();
			readRecord.setArticleId(blogId);
			readRecord.setEmy(loginInfo.getEmy());
			readRecord.setIpAddr(loginInfo.getIp());
			readRecord.setStartTime(LocalDateTime.now());
			// 临时写法
			readRecord.setFromUrl(loginInfo.getFromUrl());
			readRecord.setEndTime(LocalDateTime.now());
			readRecordService.save(readRecord);
		}
        return result;
	}



	@Override
	public PageResult<BlogArticleVo> getBlogListOfAuthor(PageRequest<String> pageRequest) {
		PageResult<BlogArticle> articlePage = blogArticleManager
				.getBlogListByAuthor(pageRequest);
		List<BlogArticle> articleList = articlePage.getList();
		if (CollectionUtils.isEmpty(articleList)){
			return PageResult.emptyList();
		}
		List<BlogArticleVo> blogArticleVoList = MyBeanCopier.copyList(articleList, BlogArticleVo::new);
		for (int i =0; i< articleList.size(); i++) {
			blogArticleVoList.get(i).setTitle(articleList.get(i).getArticleTitle());
			blogArticleVoList.get(i).setAuthor(articleList.get(i).getAuthor());
			blogArticleVoList.get(i).setDes(articleList.get(i).getArticleDes());
			blogArticleVoList.get(i).setHtmlContent(articleList.get(i).getArticleContent());
			blogArticleVoList.get(i).setTextContent(articleList.get(i).getArticleText());
		}
		return PageResult.success(blogArticleVoList, articlePage.getTotal());
	}

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public Result<Long> updateBlog(BlogSaveReq blog) {
		BlogArticle blogArticle = blogArticleManager.getBlogArticleById(blog.getId());
		if (blogArticle == null){
			return Result.fail("博客信息不存在！");
		}
		blogArticle.setArticleTitle(blog.getTitle());
		blogArticle.setArticleDes(blog.getDes());
		blogArticle.setArticleContent(blog.getHtmlContent());
		blogArticle.setArticleText(blog.getTextContent());
		blogArticle.setAuthor(blog.getAuthor());
		blogArticle.setArticleStatus(BlogStatusEnum.DEFAULT.getCode());
		blogArticle.recordUpdateInfo(blog.getAuthor(), "更新文章信息");
		this.updateById(blogArticle);
		return Result.success();
	}

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public Result<Boolean> deleteBlog(Long blogId, String author) {
		BlogArticle blogArticle = blogArticleManager.getBlogArticleById(blogId);
		if (blogArticle == null){
			return Result.fail("博客信息不存在！");
		}
		blogArticle.setDelFlag(DelEnum.deleted());
		blogArticle.recordUpdateInfo(author, "删除文章信息");
		this.updateById(blogArticle);
		return Result.success();
	}

	@Override
	@Transactional(rollbackFor = {Exception.class})
	public Result<Long> publishBlog(BlogPublishReq blogPublishReq) {
		if (blogPublishReq == null) {
			return Result.fail("博客文章信息为空！");
		}

		BlogArticle blogArticle = new BlogArticle();

		// 保存文章主表信息
		BlogSaveReq blogSaveReq = blogPublishReq.getArticleInfo();

		if (blogSaveReq.getId() == null) {
			blogArticle.recordCreateInfo(blogSaveReq.getAuthor(), "发布新博客文章");
		}else {
			blogArticle = blogArticleManager.getBlogArticleById(blogSaveReq.getId());
			if (blogArticle == null){
				return Result.fail("博客文章信息不存在");
			}
			blogArticle.recordUpdateInfo(blogSaveReq.getAuthor(), "更新发布博客文章");
		}

        blogArticle.setArticleTitle(blogSaveReq.getTitle());
		blogArticle.setArticleDes(blogSaveReq.getDes());
		blogArticle.setArticleContent(blogSaveReq.getHtmlContent());
		blogArticle.setAuthor(blogSaveReq.getAuthor());
		blogArticle.setArticleText(blogSaveReq.getTextContent());

		blogArticle.setArticleType(blogPublishReq.getType());
		if (blogPublishReq.isHasCover()) {
			blogArticle.setCoverUrl(blogPublishReq.getCoverUrl());
		}
		blogArticle.setPublishType(blogPublishReq.getPublishType());

		if (BlogTypeEnum.DEFAULT.getCode() != blogPublishReq.getPublishType()) {
			blogArticle.setQuote(blogPublishReq.getQuote());
		}
		// 设置状态为发布状态， 开启审批流程
		blogArticle.setArticleStatus(BlogStatusEnum.PUBLISH.getCode());

		this.saveOrUpdate(blogArticle);

		// 保存文章标签信息
		List<String> tags = blogPublishReq.getTags();
		tagsService.saveOrUpdateTags(tags, blogArticle);

		return Result.success(blogArticle.getId());
	}

}
