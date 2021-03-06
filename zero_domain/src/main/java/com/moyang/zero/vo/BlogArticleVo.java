package com.moyang.zero.vo;

import com.moyang.zero.entity.Template;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: moyang
 * @ClassName: BlogVo
 * @Date: 2022/2/4 1:24
 * @Description: 展示的博客文章信息
 * @Version: V1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogArticleVo extends Template {

	private String title;

	private String des;

	private String htmlContent;

	private String textContent;

	private String author;

	private Integer articleType;

	private Integer articleStatus;

	private String coverUrl;

	private Integer publishType;

	private String quote;

	private LocalDateTime publishTime;

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

	/**
	 * 点赞次数
	 */
	private int likeCount;

	/**
	 * 评论次数
	 */
	private int commentCount;



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
