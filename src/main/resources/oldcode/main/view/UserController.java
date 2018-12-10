package com.group12.course.view;

import com.alibaba.fastjson.JSON;
import com.group12.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User controller
 * @author Xu Gang
 * @date 2018年11月29日
*/

@RestController

@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 通过前端传来的账号密码，判断密码是否符合
     * @param user 通过前端传入的数据组成的 user 对象
     * 若用户输入正确，则返回状态码 200，并且返回 JSON 数据，
     * key 为 isActive，value 值为 false 或 true
     * 若用户输入错误，则返回状态码 410
     */
    @PostMapping(value = "/login")
    public void checkUser(@RequestBody User user,HttpServletResponse response)throws IOException {
        User returnUser = userService.checkUser(user.getAccount(),user.getPassword());
        if (!user.getAccount().equals(returnUser.getAccount())){
            response.setStatus(410);
        }
        else{
            Map map=new HashMap(1);
            map.put("isActive",returnUser.getActive());
            String  param= JSON.toJSONString(map);
            response.setStatus(200);
            response.getWriter().write(param);
        }
    }

    /**
     * 通过传入的用户信息，修改密码
     * @param user 用户
     * 若修改成功，返回 200，失败则 410
     */
    @PostMapping(value = "/changePassword")
    public void changePassword(@RequestBody User user,HttpServletResponse response){
        int modifyStatus=userService.changePassword(user);
        if (modifyStatus!=0){
            response.setStatus(200);
        }
        else {
            response.setStatus(410);
        }
    }

    /**
     * 添加用户
     * @param user 用户
     * 若成功创建，返回200
     * 若创建失败，返回410
     * */
    @PostMapping(value = "/addUser")
    public void addUser(@RequestBody User user,HttpServletResponse response){
        int status=userService.addUser(user);
        if (status!=0){
            response.setStatus(200);
        }
        else {
            response.setStatus(410);
        }
    }

    /**
     * 删除用户
     * @param account 用户账号
     * 若成功删除，返回200
     * 若创建失败，返回410
     * */
    @GetMapping(value = "/deleteUser/{account}")
    public void deleteUser(@PathVariable String account,HttpServletResponse response){
        int status=userService.deleteUser(account);
        if (status!=0){
            response.setStatus(200);
        }
        else {
            response.setStatus(410);
        }
    }

    /**
     * 通过传入的用户信息，修改邮箱
     * @param user 用户
     * 若修改成功，返回 200，失败则 410
     */
    @PostMapping(value = "/changeEmail")
    public void changeEmail(@RequestBody User user,HttpServletResponse response){
        int modifyStatus=userService.changeEmail(user);
        if (modifyStatus!=0){
            response.setStatus(200);
        }
        else {
            response.setStatus(410);
        }
    }

    /**
     * 通过传入的用户信息，修改姓名
     * @param user 用户
     * 若修改成功，返回 200，失败则 410
     */
    @PostMapping(value = "/changeName")
    public void changeName(@RequestBody User user,HttpServletResponse response){
        int modifyStatus=userService.changeName(user);
        if (modifyStatus!=0){
            response.setStatus(200);
        }
        else {
            response.setStatus(410);
        }
    }

    /**
     * 通过传入的用户信息，修改激活状态
     * @param user 用户
     * 若修改成功，返回 200，失败则 410
     */
    @PostMapping(value = "/changeStatus")
    public void changeActiveStatus(@RequestBody User user,HttpServletResponse response){
        int modifyStatus=userService.changeActiveStatus(user);
        if (modifyStatus!=0){
            response.setStatus(200);
        }
        else {
            response.setStatus(410);
        }
    }

    /**
     * 发送验证码
     * @param user 要含有邮箱的用户信息
     * 是否发送成功
     * 发送成功返回状态码 200，并且返回 JSON 格式数据
     * 其中 key 为 code，value 为验证码的值
     * 若失败则返回状态码 410，并且返回 JSON 格式数据
     * 其中 key 为 errMsg，value 为错误信息
     * */
    @PostMapping(value = "/getVerifyCode")
    public void getVerifyCode(@RequestBody User user,HttpServletResponse response)throws IOException{
        String status=userService.getVerificationCode(user);
        final int codeLength=6;
        if (status.length()==codeLength){
            Map map=new HashMap(1);
            map.put("code",status);
            String  param= JSON.toJSONString(map);
            response.setStatus(200);
            response.getWriter().write(param);
        }
        else {
            Map map=new HashMap(1);
            map.put("errMsg",status);
            String param= JSON.toJSONString(map);
            response.setStatus(410);
            response.getWriter().write(param);
        }
    }

    /**
     * 返回用户信息
     * */
    @PostMapping(value = "/getUserInfo")
    public User getUserInfo(@RequestBody User user,HttpServletResponse response)throws IOException{
        response.setStatus(200);
        return userService.getUserInfo(user);
    }


    /**
     * 上传学生名单
     * */
    @PostMapping(value = "/uploadStudentList")
    public void uploadStudentList(@RequestParam("file") MultipartFile file,HttpServletResponse response){
        response.setStatus(userService.uploadStudentList(file)?200:410);
    }
}
