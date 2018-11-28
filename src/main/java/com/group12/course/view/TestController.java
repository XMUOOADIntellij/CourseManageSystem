package com.group12.course.view;

import com.group12.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class TestController {

    @Autowired
    CourseService courseService;
    
    @RequestMapping(value = "/", method = GET)
    public String login(Model model) {
        return "login";
    }



    @RequestMapping(value = "/vali_psw", method = GET)
    public String valiPsw(Model model) {
        return "vali_psw";
    }

    @RequestMapping(value = "/email-modify", method = GET)
    public String emailModify(Model model) {
        return "email-modify";
    }
}