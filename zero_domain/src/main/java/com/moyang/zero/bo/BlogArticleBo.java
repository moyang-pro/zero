package com.moyang.zero.bo;

import com.moyang.zero.entity.BlogArticle;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: moyang
 * @ClassName: BlogArticleBo
 * @Date: 2022/2/12 14:26
 * @Description: 博客文章全量信息
 * @Version: V1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class BlogArticleBo extends BlogArticle {

	/**
	 * 浏览量
	 */
	private int readCount;

	/**
	 * 阅读人数
	 */
	private int userCount;

	/**
	 * 点击量
	 */
	private int clickCount;

	/**
	 * 收藏次数
	 */
	private int collectCount;


}
