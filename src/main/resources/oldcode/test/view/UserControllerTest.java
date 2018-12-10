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
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;
import java.util.TreeMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * User controller层测试
 * @author Xu Gang
 * @date 2018年12月1日
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @Before
    public void setUp(){
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testCheckUser() throws Exception{
        Map<String,String> rightAccount = new TreeMap<>();
        rightAccount.put("account","987654321");
        rightAccount.put("password","987654321");
        MvcResult rightResult =
                mvc.perform(MockMvcRequestBuilders.post("/api/user/login")
                        // 设置请求内容为JSON格式
                        .contentType(MediaType.APPLICATION_JSON)
                        // 将请求内容传入
                        .content(JSONObject.toJSONString(rightAccount)))
                        // 验证执行的控制器类型
                        .andExpect(handler().handlerType(UserController.class))
                        // 验证执行的控制器方法名
                        .andExpect(handler().methodName("checkUser"))
                        // 验证状态码
                        .andExpect(status().is(200))
                        .andReturn();

        Map<String,String> wrongAccount = new TreeMap<>();
        wrongAccount.put("account","24320162202934");
        wrongAccount.put("password","1234567");
        MvcResult wrongResult =
                mvc.perform(MockMvcRequestBuilders.post("/api/user/login")
                        // 设置请求内容为JSON格式
                        .contentType(MediaType.APPLICATION_JSON)
                        // 将请求内容传入
                        .content(JSONObject.toJSONString(wrongAccount)))
                        // 验证执行的控制器类型
                        .andExpect(handler().handlerType(UserController.class))
                        // 验证执行的控制器方法名
                        .andExpect(handler().methodName("checkUser"))
                        // 验证状态码
                        .andExpect(status().is(410))
                        .andReturn();
    }

    @Test
    public void testChangPassword() throws Exception{
        Map<String,String> rightAccount = new TreeMap<String, String>();
        rightAccount.put("account","987654321");
        rightAccount.put("password","123456");
        MvcResult rightResult =
                mvc.perform(MockMvcRequestBuilders.post("/api/user/changePassword")
                        // 设置请求内容为JSON格式
                        .contentType(MediaType.APPLICATION_JSON)
                        // 将请求内容传入
                        .content(JSONObject.toJSONString(rightAccount)))
                        // 验证执行的控制器类型
                        .andExpect(handler().handlerType(UserController.class))
                        // 验证执行的控制器方法名
                        .andExpect(handler().methodName("changePassword"))
                        // 验证状态码
                        .andExpect(status().is(200))
                        .andReturn();

        Map<String,String> wrongAccount = new TreeMap<String, String>();
        wrongAccount.put("account","243201622029345");
        wrongAccount.put("password","1234568");
        MvcResult wrongResult =
                mvc.perform(MockMvcRequestBuilders.post("/api/user/changePassword")
                        // 设置请求内容为JSON格式
                        .contentType(MediaType.APPLICATION_JSON)
                        // 将请求内容传入
                        .content(JSONObject.toJSONString(wrongAccount)))
                        // 验证执行的控制器类型
                        .andExpect(handler().handlerType(UserController.class))
                        // 验证执行的控制器方法名
                        .andExpect(handler().methodName("changePassword"))
                        // 验证状态码
                        .andExpect(status().is(410))
                        .andReturn();
    }

    @Test
    public void testAddUser()throws Exception{
        Map<String,String> rightAccount = new TreeMap<String, String>();
        rightAccount.put("account","29320162202985");
        rightAccount.put("password","123456");
//        rightAccount.put("email","1234@qq.com");
//        rightAccount.put("name","name");
//        rightAccount.put("active","1");
        MvcResult rightResult =
                mvc.perform(MockMvcRequestBuilders.post("/api/user/addUser")
                        // 设置请求内容为JSON格式
                        .contentType(MediaType.APPLICATION_JSON)
                        // 将请求内容传入
                        .content(JSONObject.toJSONString(rightAccount)))
                        // 验证执行的控制器类型
                        .andExpect(handler().handlerType(UserController.class))
                        // 验证执行的控制器方法名
                        .andExpect(handler().methodName("addUser"))
                        // 验证状态码
                        .andExpect(status().is(200))
                        .andReturn();
    }

    @Test
    public void testGetVerifyCode()throws Exception{
        Map<String,String> rightAccount = new TreeMap<String, String>();
        rightAccount.put("account","24320162202985");
        rightAccount.put("email","277030573@qq.com");
        MvcResult rightResult =
                mvc.perform(MockMvcRequestBuilders.post("/api/user/getVerifyCode")
                        // 设置请求内容为JSON格式
                        .contentType(MediaType.APPLICATION_JSON)
                        // 将请求内容传入
                        .content(JSONObject.toJSONString(rightAccount)))
                        // 验证执行的控制器类型
                        .andExpect(handler().handlerType(UserController.class))
                        // 验证执行的控制器方法名
                        .andExpect(handler().methodName("getVerifyCode"))
                        // 验证状态码
                        .andExpect(status().is(200))
                        .andReturn();

        Map<String,String> wrongAccount = new TreeMap<String, String>();
        wrongAccount.put("account","24320162202985");
        MvcResult wrongResult =
                mvc.perform(MockMvcRequestBuilders.post("/api/user/getVerifyCode")
                        // 设置请求内容为JSON格式
                        .contentType(MediaType.APPLICATION_JSON)
                        // 将请求内容传入
                        .content(JSONObject.toJSONString(wrongAccount)))
                        // 验证执行的控制器类型
                        .andExpect(handler().handlerType(UserController.class))
                        // 验证执行的控制器方法名
                        .andExpect(handler().methodName("getVerifyCode"))
                        // 验证状态码
                        .andExpect(status().is(410))
                        .andReturn();
    }
}
