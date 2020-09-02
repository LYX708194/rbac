package com.example.rbac.handle;

import org.springframework.stereotype.Component;

/**
 * @author lyx
 * @date 2020/8/17 20:53
 */
@Component
//@RestControllerAdvice
public class ExceptionHandler {

    /**
     * 异常处理
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception ex) {

        System.out.println(ex.getMessage());
        return ex.getMessage();
    }

}
