package com.group12.course.view;


import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Y Jiang
 * @date 2018/11/30
 *
 * 测试Course的Controller层
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest

public class CourseControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setUp(){
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetAllCourses()throws Exception{
        Map<String,String> request = new TreeMap<>();
        request.put("id","1");
        MvcResult mvcResult =
                mvc.perform(MockMvcRequestBuilders.get("/api/course/getall")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(JSONObject.toJSONString(request)))
                        //验证执行的控制器类型
                        .andExpect(handler().handlerType(CourseController.class))
                        //验证执行的控制器方法名
                        .andExpect(handler().methodName("listCourses"))
                        //验证状态码
                        .andExpect(status().isOk())
                        //验证contentType
                        .andExpect(content().contentType("application/json;charset=UTF-8"))
                        // 可以打印结果
                        //.andDo(print())
                        .andReturn();

    }

    @Test
    public void testDeleteCourse() throws Exception {
        MvcResult result =
                mvc.perform(MockMvcRequestBuilders.delete("/api/course/delete/{id}", 1))
                        .andExpect(handler().handlerType(CourseController.class))
                        //验证执行的控制器方法名
                        .andExpect(handler().methodName("deleteCourse"))
                        //验证状态码
                        .andExpect(status().isOk())
                        //验证contentType
                        .andExpect(content().contentType("application/json;charset=UTF-8"))
                        // 可以打印结果
                        //.andDo(print())
                        .andReturn();
    }

    @Test
    public  void testUpdateCourse() throws Exception{
        Map<String,String> requestBody = new TreeMap<>();
        requestBody.put("id", "2");
        requestBody.put("teacherNum","1");
        requestBody.put("courseName", "J3EE");
        requestBody.put("introduction", "J2EE introduction");
        requestBody.put("prePercentage", "30");
        requestBody.put("reportPercentage", "40");
        requestBody.put( "quesPercentage", "30");
        requestBody.put("teamStartDate", "2018-12-13T07:48:00");
        requestBody.put("teamEndDate", "2018-12-30T07:48:04");

        MvcResult result =
                mvc.perform(MockMvcRequestBuilders.put("/api/course/update")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(JSONObject.toJSONString(requestBody)))
                        .andExpect(handler().handlerType(CourseController.class))
                        //验证执行的控制器方法名
                        .andExpect(handler().methodName("updateCourse"))
                        //验证状态码
                        .andExpect(status().isOk())
                        //验证contentType
                        .andExpect(content().contentType("application/json;charset=UTF-8"))
                        // 可以打印结果
                        //.andDo(print())
                        .andReturn();

    }

    @Test
    public  void testAddCourse() throws  Exception{
        Map<String,String> requestBody = new TreeMap<>();
        // requestBody.put("id", "9");
        requestBody.put("teacherNum","1");
        requestBody.put("courseName", "J3EE");
        requestBody.put("introduction", "J2EE introduction");
        requestBody.put("prePercentage", "30");
        requestBody.put("reportPercentage", "40");
        requestBody.put("quesPercentage", "30");
        requestBody.put("teamStartDate", "2018-12-13T07:48:00");
        requestBody.put("teamEndDate", "2018-12-30T07:48:04");

        MvcResult result =
                mvc.perform(MockMvcRequestBuilders.post("/api/course/add")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(JSONObject.toJSONString(requestBody)))
                        .andExpect(handler().handlerType(CourseController.class))
                        //验证执行的控制器方法名
                        .andExpect(handler().methodName("addCourse"))
                        //验证状态码
                        .andExpect(status().isOk())
                        //验证contentType
                        .andExpect(content().contentType("application/json;charset=UTF-8"))
                        // 可以打印结果
                        //.andDo(print())
                        .andReturn();
    }

    @Test
    public void testGetCourses() throws Exception{
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/api/course/{id}",1))
                .andExpect(handler().handlerType(CourseController.class))
                //验证执行的控制器方法名
                .andExpect(handler().methodName("getCourse"))
                //验证状态码
                .andExpect(status().isOk())
                //验证contentType
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                // 可以打印结果
                //.andDo(print())
                .andReturn();
    }
}
