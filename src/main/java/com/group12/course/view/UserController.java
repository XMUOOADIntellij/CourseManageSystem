package com.group12.course.view;

import com.group12.course.entity.User;
import com.group12.course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 * User controller
 * @author Xu Gang
 * @date 2018年11月29日
 */

@RestController

@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @PostMapping(value = "/login",produces = "application/json")
    public User getUserByAccount(@RequestBody User user){
        return userService.getUserByAccount(user.getAccount());
    }

}
