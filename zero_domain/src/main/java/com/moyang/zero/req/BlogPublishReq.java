package com.moyang.zero.req;

import lombok.Data;

import java.util.List;

/**
 * @Author: moyang
 * @ClassName: BlogPublishReq
 * @Date: 2022/2/11 0:49
 * @Description: 博客发布请求
 * @Version: V1.0
 **/
@Data
public class BlogPublishReq {

	private boolean hasCover;

	private String coverUrl;

	private int type;

	private List<String>  tags;

	private int publishType;

	private String quote;

	private BlogSaveReq articleInfo;

}
