package com.group12.course.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Y Jiang
 * @date 2018/11/30
 *
 * 测试Course的Controller层
 */

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

     @Test
    public void testGetAllCoursesController()throws Exception{
         MvcResult mvcResult =
                 mvc.perform(MockMvcRequestBuilders.get("/course/getAll"))
                         //验证执行的控制器类型
                         .andExpect(handler().handlerType(CourseController.class))
                         //验证执行的控制器方法名
                         .andExpect(handler().methodName("getAllCourse"))
                         //验证状态码
                         .andExpect(status().isOk())
                         //验证contentType
                         .andExpect(content().contentType("application/json;charset=UTF-8"))
                         // 可以打印结果
                         // .andDo(print())
                         .andReturn();

     }


}
