package com.moyang.zero.utils.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ResponseCode
 * 统一返回值code枚举
 *
 * @author moyang
 * @date 2019/11/14 17:26
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    /**
     * 成功
     */
    SUCCESS(200, "成功"),

    /**
     * 失败
     */
    FAIL(400, "失败"),

    /**
     * 资源不存在
     */
    NO_EXISTS(404, "资源不存在"),

    /**
     * 未登录
     */
    NO_LOGIN(401, "未登录");


    /**
     * code
     */
    private final int code;

    /**
     * 错误信息
     */
    private final String message;
}
