package com.moyang.zero.advice;

import com.alibaba.fastjson.JSON;
import com.moyang.zero.common.exception.BusinessException;
import com.moyang.zero.common.util.http.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * LjControllerAdvice
 * 统一异常处理
 *
 * @author sunjialin10
 * @date 2019/11/14 17:13
 */
@RestControllerAdvice(basePackages = {"com.jd.icc.workbench.web.controller"})
@Slf4j
public class ZeroRestControllerAdvice implements ResponseBodyAdvice<Object> {

    @ExceptionHandler(Exception.class)
    public Result<String> exception(Exception e) {
        log.error("统一异常处理", e);
        if (e instanceof BusinessException) {
            return Result.fail(e.getMessage());
        }
        if (e instanceof BindException) {
            BindingResult re = ((BindException) e).getBindingResult();
            List<ObjectError> allErrors = re.getAllErrors();
            List<String> collect = allErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
            return Result.fail(StringUtils.join(collect));
        }
        return Result.fail("内部异常");
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o == null) {
            return Result.success();
        }
        if (o instanceof Result) {
            return (Result<Object>) o;
        }
        if (o instanceof String) {
            return JSON.toJSON(Result.success(o)).toString();
        }
        return Result.success(o);
    }
}
