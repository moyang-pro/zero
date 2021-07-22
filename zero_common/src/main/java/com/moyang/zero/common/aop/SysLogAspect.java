package com.moyang.zero.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Description 日志打印切面
 * @ClassName SysLogAspect
 * @Author moyang
 * @Email 1542051400@qq.com
 * @date 2021.07.22 23:28
 */
@Aspect
public class SysLogAspect {

    /**
     * 定义切入点为注解@OperationLog
     */
    @Pointcut("@annotation(com.moyang.zero.common.annotation.OperationLog)")
    public void logPointCut(){
    }

    @Around(value = "logPiontCut()")
    public Object printLog(ProceedingJoinPoint point) throws Throwable {
       Object  obj = point.proceed();
       return obj;
    }
}
