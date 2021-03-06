package com.group12.course.controller;


import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;
import java.util.TreeMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CourseControllerTest {


    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setUp(){
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

//    @Test
//    public void testCreateCourse(){
//        Map<String,String> request = new TreeMap<>();
//        request.put("courseId","1");
//        request.put("seminarName","test");
//        request.put("maxTeam","3");
//        request.put("visible","false");
//        request.put("seminarSerial","2");
//        request.put("roundId","1");
//        //request.put("enrollStartTime",LocalDateTime.now().toString());
//
//        MvcResult mvcResult =
//                mvc.perform(MockMvcRequestBuilders.post("/seminar")
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .content(JSONObject.toJSONString(request)))
//                        //验证执行的控制器类型
//                        .andExpect(handler().handlerType(SeminarController.class))
//                        //验证执行的控制器方法名
//                        .andExpect(handler().methodName("createSeminar"))
//                        //验证状态码
//                        .andExpect(status().isOk())
//                        //验证contentType
//                        .andExpect(content().contentType("application/json;charset=UTF-8"))
//                        // 可以打印结果
//                        //.andDo(print())
//                        .andReturn();
//    }

}
