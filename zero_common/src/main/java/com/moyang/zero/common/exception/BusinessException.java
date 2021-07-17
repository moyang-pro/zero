package com.moyang.zero.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p> 业务异常 </p >
 * @author moyanghualing@163.com
 * @date 2018/11/30 16:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

    /**
     * 异常码
     */
    private String code;

    /**
     * 异常描述
     */
    private String msg;

    /**
     * 构造异常
     * @param code 异常码
     * @param msg 异常描述
     */
    public BusinessException(String code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
        this.msg = msg;
    }

    /**
     * 构造异常
     * @param code 异常码
     * @param msg 异常描述
     */
    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BusinessException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

}