package com.group12.course.controller;

import com.group12.course.entity.Seminar;
import com.group12.course.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 讨论课部分的 Controller
 * @author Y Jiang
 * @date  2018/12/12
 */
@RestController
@RequestMapping("/seminar")
public class SeminarController {
    @Autowired
    SeminarService seminarService;

    @PostMapping(value= "" , produces = "application/json; charset=utf-8")
    public int createSeminar(@RequestBody Seminar seminar) throws Exception{
        //TODO
       return 0;
    }


}
