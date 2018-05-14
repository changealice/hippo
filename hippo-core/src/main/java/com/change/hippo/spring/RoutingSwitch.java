package com.change.hippo.spring;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: change.long
 * Date: 2017/10/8
 * Time: 下午4:50
 * 标记需要切换的方法
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RoutingSwitch {

    /**
     * 实时获取开关值，来决定调用那个版本
     */
    String value() default "";
}
