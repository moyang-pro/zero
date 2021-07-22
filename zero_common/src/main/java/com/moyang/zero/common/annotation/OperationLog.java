package com.moyang.zero.common.annotation;

import java.lang.annotation.*;

/**
 * @Description 打印操作日志
 * @ClassName OperationLog
 * @Author moyang
 * @date 2021.07.22 23:22
 */
@Target(value = {ElementType.METHOD,ElementType.CONSTRUCTOR,ElementType.MODULE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {
    String value() default "";
}
