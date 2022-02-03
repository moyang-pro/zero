package com.moyang.zero.req;

import lombok.Data;

/**
 * @Author: moyang
 * @ClassName: BlogSaveReq
 * @Date: 2022/2/3 18:43
 * @Description: 保存博客文章 模型
 * @Version: V1.0
 **/
@Data
public class BlogSaveReq {

	private String title;

	private String des;

	private String htmlContent;

	private String author;
}
