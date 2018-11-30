package com.group12.course.view;

import com.group12.course.entity.User;
import com.group12.course.service.UserService;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User controller
 * @author Xu Gang
 * @date 2018年11月29日
*/

@RestController

@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 通过前端传来的账号密码，判断密码是否符合
     * @param user 通过前端传入的数据组成的 user 对象
     * @return  若用户输入正确，则返回正确的用户对象的JSON，状态码200，
     * 若用户输入错误，则返回状态码410
     */
    @PostMapping(value = "/login")
    public void checkUser(@RequestBody User user,HttpServletResponse response) throws IOException {
        User returnUser = userService.checkUser(user.getAccount(),user.getPassword());
        if (!user.getAccount().equals(returnUser.getAccount())){
            response.setStatus(410);
        }
        else{
            response.setStatus(200);
            response.getWriter().write("{\"username\":\"zhang\",\"passwd\":\"123\"}");
        }

        /**
         * TODO
         * 异常处理*/
    }

    /**
     * 通过传入的用户信息，修改密码
     * @param user 用户
     * @return 若修改成功，返回 200，失败则 410
     */
    @PostMapping(value = "changePassword")
    public void changPassword(@RequestBody User user,HttpServletResponse response)throws IOException{
        Boolean modifyStatus=userService.changePassword(user);
        if (modifyStatus){
            response.setStatus(200);
        }
        else {
            response.setStatus(410);
        }
    }
}
