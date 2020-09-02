package com.example.rbac.controller;

import com.example.rbac.annotation.VisitPermission;
import com.example.rbac.po.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyx
 * @date 2020/8/17 20:04
 */
@RestController
@RequestMapping("/permission")
public class TestController {

    /**
     * 只有拥有权限 test 的才可以访问
     * @return
     */
    @VisitPermission("test")
    @GetMapping("/test")
    public Result test(){
        return new Result(200,"success2",null);
    }

}
