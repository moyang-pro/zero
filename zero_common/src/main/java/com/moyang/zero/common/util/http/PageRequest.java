package com.moyang.zero.common.util.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: moyang
 * @ClassName: PageRequest
 * @Date: 2022/2/6 16:42
 * @Description: 分页请求
 * @Version: V1.0
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest<T> {

	private static int DEFAULT_PAGE_INDEX = 1;

	private static int DEFAULT_PAGE_SIZE = 5;

	/**
	 * 页码
	 */
	private int pageIndex;
	/**
	 * 每页的数量
	 */
	private int pageSize;
	/**
	 * 查询的关键词
	 */
	private String keyword;

	/**
	 * 查询的自定义数据
	 */
	private T data;

	/**
	 * 获取页码 默认值 1
	 * @return 页码
	 */
	public int getPageIndex() {
		return Math.max(DEFAULT_PAGE_INDEX, pageIndex);
	}

	/**
	 * 获取每页大小 默认值 1
	 * @return 每页大小
	 */
	public int getPageSize() {
		return Math.max(DEFAULT_PAGE_SIZE, pageSize);
	}

}
