package com.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 我自己的注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)     // 保障此注解不会在运行时被干掉
public @interface ShenyiAnnotation {
    String name() default "shenyi";
    int age() default 18;
}
