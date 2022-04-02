package com.moyang.zero.vo;

import com.moyang.zero.entity.Template;
import lombok.*;

import java.time.LocalDate;

/**
 * @Author: moyang
 * @ClassName: BlogUserVo
 * @Date: 2022/3/5 0:17
 * @Description: 博客系统 用户信息vo
 * @Version: V1.0
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogUserVo extends Template {

	/**
	 * 博客博主emy
	 */
	private String blogUser;

	/**
	 * 性别(1:男2:女)
	 */
	private Integer gender;

	/**
	 * 作者头像
	 */
	private String avatar;

	private LocalDate birthday;

	private String summary;

	/**
	 * 作者昵称
	 */
	private String nickName;

	private String qqNumber;

	private String weChat;

	private String occupation;

	private String github;

	private String gitee;

	/**
	 * 博主是否为VIP用户 0否 1普通VIP 2超级VIP
	 */
	private Integer vipCode;

	private Integer point;

	private Integer articleCount;

	private Integer followCount;

	private Integer followedCount;

	private Integer faqCount;

	/**
	 * 是否已经关注作者
	 */
	private boolean hasFollowed;

//	/**
//	 * 是否已经收藏文章
//	 */
//	private boolean hasCollected;
//
//
//	/**
//	 * 是否已经点赞文章
//	 */
//	private boolean hasLiked;
}
