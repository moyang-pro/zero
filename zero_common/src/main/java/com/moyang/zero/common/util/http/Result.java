package com.moyang.zero.common.util.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>统一返回结果</p>
 *
 * @author: moyang
 * @email: moyanghualing@163.com
 * @Date: 2020/3/4 14:49
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result<T> implements Serializable {
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

    /**
     * 返回值内容
     */
    private T data;

    /**
     * 总数
     */
    private long total;

    public Result(boolean success, int code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * success
     *
     * @return Result
     */
    public static <T> Result<T> success() {
        return new Result<>(true, ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), null);
    }

    /**
     * success
     *
     * @param data data
     * @return result
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(true, ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * success
     *
     * @param data  data
     * @param total total
     * @return result
     */
    public static <T> Result<T> success(T data, long total) {
        return new Result<>(true, ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data, total);
    }

    /**
     * success
     *
     * @param data  data
     * @param total total
     * @return result
     */
    public static <T> Result<T> success(int code, String message, T data, long total) {
        return new Result<>(true, code, message, data, total);
    }

    /**
     * fail
     *
     * @return result
     */
    public static <T> Result<T> fail() {
        return new Result<>(false, ResultCodeEnum.FAIL.getCode(), ResultCodeEnum.FAIL.getMessage(), null);
    }

    /**
     * fail
     *
     * @param code code
     * @return result
     */
    public static <T> Result<T> fail(int code, String message) {
        return new Result<>(false, code, message, null);
    }

    /**
     * fail
     *
     * @param total total
     * @return result
     */
    public static <T> Result<T> fail(String message, T data, long total) {
        return new Result<>(false, ResultCodeEnum.FAIL.getCode(), message, data, total);
    }

    /**
     * fail
     *
     * @return result
     */
    public static <T> Result<T> fail(String message) {
        return new Result<>(false, ResultCodeEnum.FAIL.getCode(), message, null);
    }

    /**
     * fail
     *
     * @param code  code
     * @param total total
     * @param data  data
     */
    public static <T> Result<T> fail(int code, String message, T data, long total) {
        return new Result<>(false, code, message, data, total);
    }

    /**
     * @param codeEnum codeEnum
     * @param total    total
     * @return Result
     */
    public static <T> Result<T> fail(ResultCodeEnum codeEnum, T data, long total) {
        return new Result<>(false, codeEnum.getCode(), codeEnum.getMessage(), data, total);
    }

}
