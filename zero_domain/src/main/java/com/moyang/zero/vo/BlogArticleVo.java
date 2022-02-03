package com.moyang.zero.vo;

import com.moyang.zero.entity.Template;
import lombok.*;

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

	private String author;

}
