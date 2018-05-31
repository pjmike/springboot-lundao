package com.pjmike.lundao.web.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/**
 * @author pjmike
 * @create 2018-05-19 22:48
 */
@Aspect
@Component
@Slf4j
public class ServiceAspect {
    @Pointcut("@annotation(ServiceMonitor)")
    public void pointcut() {

    }
    @Before("pointcut()")
    public void logMethodInvokeParam(JoinPoint joinPoint) {
        log.info("---Before method {} invoke, param: {}---", joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "pointcut()", returning = "retVal")
    public void logMethodInvokeResult(JoinPoint joinPoint, Object retVal) {
        log.info("---After method {} invoke, result: {}---", joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "exception")
    public void logMethodInvokeException(JoinPoint joinPoint, Exception exception) {
        log.info("---method {} invoke exception: {}---", joinPoint.getSignature().toShortString(), exception.getMessage());
    }
}
