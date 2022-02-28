package com.moyang.zero.bo;

import com.moyang.zero.entity.BlogArticle;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

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
	 * 作者头像
	 */
	private String authorAvatar;

	/**
	 * 博主是否为VIP用户 0否 1普通VIP 2超级VIP
	 */
	private int vipCode;

	/**
	 * 作者昵称
	 */
	private String nick;

	/**
	 * 文章标签
	 */
	private List<String> tags;

}
