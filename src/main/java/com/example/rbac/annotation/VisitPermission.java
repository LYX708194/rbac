package com.example.rbac.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限访问控制
 * @author lyx
 * @date 2020/8/17 19:47
 */
@Target({ElementType.METHOD})   //注解的作用目标：方法
@Retention(RetentionPolicy.RUNTIME)  //注解会在class字节码文件中存在，在运行时可以通过反射获取到
public @interface VisitPermission {


    /**
     * 用于配置具体接口的权限值
     * 在数据库添加对应记录
     * 用户登录时，将用户权限列表放入redis中
     * 用户访问接口时，将对应接口的值和redis中的匹配看是否有访问权限
     * 用户退出登录时，清空redis中对应的权限缓存
     */
    String value() default "";

}
