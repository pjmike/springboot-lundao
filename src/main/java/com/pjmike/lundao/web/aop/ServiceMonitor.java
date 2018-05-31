package com.pjmike.lundao.web.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author pjmike
 * @create 2018-05-19 22:06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ServiceMonitor {
    public String[] ignoreLogParams() default {};
}
