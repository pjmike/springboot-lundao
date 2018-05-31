package com.pjmike.lundao.web.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 1.   使用AOP统一处理请求日志
 * 2.   Spring Boot对AOP的默认配置属性是开启的，也就是说spring.aop.auto属性的值默认是true，
 * 我们只要引入了AOP依赖后，默认就已经增加了@EnableAspectJAutoProxy功能，
 * 不需要我们在程序启动类上面加入注解@EnableAspectJAutoProxy
 *
 * @author pjmike
 * @create 2018-05-19 17:40
 **/
@Aspect
@Component
@Slf4j
public class HttpApectJs {


    /**
     * 对controller方法定义切点
     */
    @Pointcut("execution(public * com.pjmike.lundao.web.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint point) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //请求URL
        log.info("url={}", request.getRequestURL());
        //请求方法
        log.info("HTTP_Method={}", request.getMethod());
        //请求IP
        log.info("IP={}", request.getRemoteAddr());
        //请求类的方法
        log.info("Class_Method={}", point.getSignature().getDeclaringTypeName() + "." + point.getSignature().getName());
        //请求类方法的参数
        log.info("Args={}", Arrays.asList(point.getArgs()));
    }

    @AfterReturning(returning = "object", pointcut = "webLog()")
    public void doAfterReturn(Object object) {
        log.info("response={}", object.toString());
    }
}
