package com.whc.evnetdemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author whc
 * @date 2020/8/29 2:13
 * event listener 专用注解，只能加在实现了ApplicationListener 的onApplicationEvent方法上
 * 用来标识是同步监听还是异步监听
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventType {
    EventTypeEnum value() default EventTypeEnum.ASYNC;
}
