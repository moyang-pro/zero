package com.moyang.zero.utils.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p></p>
 *
 * @author: moyang
 * @email: moyanghualing@163.com
 * @Date: 2020/3/4 14:49
 * @Version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {
    /** 总数 */
    private long total;

    /** 返回列表 */
    private List<T> list;
}
