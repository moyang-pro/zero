package com.moyang.zero.common.util.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
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

    /**
     * success
     */
    private boolean success;

    /**
     * 统一返回码
     */
    private int code;

    /**
     * 错误信息
     */
    private String message;

    /** 总数 */
    private long total;

    /** 返回列表 */
    private List<T> list;

    public PageResult(boolean success, int code, String message, List<T> list, long total) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.list = list;
        this.total = total;
    }

    /**
     * success
     * @param list  list data
     * @param total total
     * @return result
     */
    public static <T> PageResult<T> success(List<T> list, long total) {
        return new PageResult<>(true, ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), list, total);
    }

    /**
     * empty 结果
     * @return result
     */
    public static <T> PageResult<T> emptyList() {
        return new PageResult<>(true, ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(),
                Collections.emptyList(), 0L);
    }
}
