package com.example.rbac.test;

import com.example.rbac.controller.TestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author lyx
 * @date 2020/8/17 20:13
 */
@SpringBootTest
public class PermissionTest {

    @Autowired
    private TestController testController;

    //mockmvc,实现队http请求的模拟，不必启动项目即可进行controller测试
    private MockMvc mvc;


    @BeforeEach
    public void setupMockMvc(){
        mvc = MockMvcBuilders.standaloneSetup(testController).build();
    }

    @Test
    public void apiTest() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/permission/test")
                .accept(MediaType.APPLICATION_JSON)
                .header("token", "test"))   //有权限
//                .header("token", "12345"))  //无权限
                .andReturn();
        System.out.println("api test result : " + result.getResponse().getContentAsString());
    }



}
