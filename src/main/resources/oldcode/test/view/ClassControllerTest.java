package com.group12.course.view;

import com.group12.course.entity.Class;
import com.group12.course.service.ClassService;
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
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;
import java.util.TreeMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 测试 Class 的 Controller 层方法
 * @author Tan Xue
 * @date 2018/12/2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ClassControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setUp(){
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGetAllClasses()throws Exception{
        Map<String,String> request = new TreeMap<>();
        request.put("id","1");
        MvcResult mvcResult =
                mvc.perform(MockMvcRequestBuilders.get("/api/class/getAll")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(JSONObject.toJSONString(request)))
                        //验证执行的控制器类型
                        .andExpect(handler().handlerType(ClassController.class))
                        //验证执行的控制器方法名
                        .andExpect(handler().methodName("getAllClasses"))
                        //验证状态码
                        .andExpect(status().isOk())
                        //验证contentType
                        .andExpect(content().contentType("application/json;charset=UTF-8"))
                        // 可以打印结果
                        //.andDo(print())
                        .andReturn();

    }

    @Test
    public void testCreateClass() throws Exception {
        Map<String,String> class1 = new TreeMap<String,String>();
        class1.put("id",new String("2"));
        class1.put("name",new String("OOAD"));
        class1.put("time",new String("周一一二节"));
        class1.put("location",new String("公寓306"));
        class1.put("courseId",new String("1"));
        MvcResult rightResult =
                mvc.perform(MockMvcRequestBuilders.post("/api/class/add")
                        // 设置请求内容为JSON格式
                        .contentType(MediaType.APPLICATION_JSON)
                        // 将请求内容传入
                        .content(JSONObject.toJSONString(class1)))
                        // 验证执行的控制器类型
                        .andExpect(handler().handlerType(ClassController.class))
                        // 验证执行的控制器方法名
                        .andExpect(handler().methodName("createClass"))
                        // 验证状态码
                        .andExpect(status().is(200))
                        .andReturn();
    }

    @Test
    public void testGetClassByClassId() throws Exception {
        MvcResult mvcResult =
                mvc.perform(MockMvcRequestBuilders.get("/api/class/{id}","1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                        //验证执行的控制器类型
                        .andExpect(handler().handlerType(ClassController.class))
                        //验证执行的控制器方法名
                        .andExpect(handler().methodName("getClassByClassId"))
                        //验证状态码
                        .andExpect(status().isOk())
                        //验证contentType
                        .andExpect(content().contentType("application/json;charset=UTF-8"))
                        // 可以打印结果
                        //.andDo(print())
                        .andReturn();
    }

    @Test
    public void testUpdateClass() throws Exception {
        Map<String,String> class1 = new TreeMap<String,String>();
        class1.put("id",new String("1"));
        class1.put("name",new String("OOAD"));
        class1.put("time",new String("周一三四节"));
        class1.put("location",new String("海韵202"));
        class1.put("courseId",new String("1"));
        MvcResult rightResult =
                mvc.perform(MockMvcRequestBuilders.put("/api/class/update")
                        // 设置请求内容为JSON格式
                        .contentType(MediaType.APPLICATION_JSON)
                        // 将请求内容传入
                        .content(JSONObject.toJSONString(class1)))
                        // 验证执行的控制器类型
                        .andExpect(handler().handlerType(ClassController.class))
                        // 验证执行的控制器方法名
                        .andExpect(handler().methodName("updateClass"))
                        // 验证状态码
                        .andExpect(status().is(200))
                        .andReturn();
    }

    @Test
    public void testDeleteClass() throws Exception {
        MvcResult result =
                mvc.perform(MockMvcRequestBuilders.delete("/api/class/delete/{id}","1"))
                        // 验证执行的控制器类型
                        .andExpect(handler().handlerType(ClassController.class))
                        // 验证执行的控制器方法名
                        .andExpect(handler().methodName("deleteClass"))
                         // 验证状态码
                        .andExpect(status().is(200))
                        .andReturn();

    }
}