package com.moyang.zero.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moyang.zero.bo.BlogArticleBo;
import com.moyang.zero.entity.BlogArticle;
import com.moyang.zero.pojo.blog.BlogSelectParam;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author moyang
 * @since 2022-02-02
 */
public interface BlogArticleMapper extends BaseMapper<BlogArticle> {


	/**
	 * 分页查询作者的全部博客文章信息
	 * @param page 分页信息
	 * @param param 查询参数
	 * @return 文章全部信息
	 */
	IPage<BlogArticleBo> selectBlogAllInfoByPage(Page<BlogArticleBo> page, @Param("param") BlogSelectParam param);


	/**
	 * 查询博客文章全量信息
	 * @param blogId 博客ID
	 * @param author 作者账号
	 * @return 文章全部信息
	 */
	BlogArticleBo selectBlogAllInfoById(@Param("blogId") Long blogId, @Param("author") String author);

}
