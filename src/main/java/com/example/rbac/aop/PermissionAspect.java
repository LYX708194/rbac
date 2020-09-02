package com.example.rbac.aop;

import com.example.rbac.annotation.VisitPermission;
import com.example.rbac.po.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author lyx
 * @date 2020/8/17 19:44
 */
@Aspect
@Component
public class PermissionAspect {


    /**
     * 切入点为注解的： @annotation(VisitPermission)
     * 存在 VisitPermission 注解的方法
     */
    @Pointcut("@annotation(com.example.rbac.annotation.VisitPermission)")
    private void permission(){

    }


    /**
     * 目标方法调用之前执行
     */
    @Before("permission()")
    public void doBefore() {

    }

    /**
     * 目标方法调用之后执行
     */
    @After("permission()")
    public void doAfter() {

    }

    /**
     * 环绕
     * 会将目标方法封装起来
     * 具体验证业务数据
     * @param proceedingJoinPoint
     * @throws Throwable
     */
    @Around("permission()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        Result result = new Result();
        /*
         * 获取当前http请求中的token
         * 解析token :
         * 1、token是否存在
         * 2、token格式是否正确
         * 3、token是否已过期（解析信息或者redis中是否存在）
         * */
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            //请求中没有带token
            result.setCode(500);
            result.setMsg("非法请求，无效token");
            return result;
        }
        // 校验token的业务逻辑
        //获得redis中的token

        //todo


        /*
         * 获取注解的值，并进行权限验证:
         * redis 中是否存在对应的权限
         * redis 中没有则从数据库中获取权限
         * 数据空中也没有，抛异常，非法请求，没有权限
         */

        //获取注解的值 如 @VisitPermission("test") 获得test权限
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        VisitPermission visitPermission = method.getAnnotation(VisitPermission.class);
        String value = visitPermission.value();


        /**
         * 校验权限的业务逻辑
         * 获得redis的权限列表
         * redis没有找到，则从数据库中再次获取权限确认
         * 从数据库中找到权限列表
         * 检查数据库中此用户是否也有存在此权限
         * 数据库中也没有，则权限不足
         */
        //todo

        //测试模拟
        if (!token.equals("test")){
            throw new Exception("无权限");
        }


        // 执行具体方法
        Object result2 = proceedingJoinPoint.proceed();

        return result2;

    }


}
