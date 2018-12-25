package com.group12.course.controller;

import com.group12.course.entity.Question;
import com.group12.course.entity.Teacher;
import com.group12.course.service.QuestionService;
import com.group12.course.tools.Jwt;
import com.group12.course.controller.vo.QuestionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 提问的controller层
 * @author Y Jiang
 * @date 2018/12/10
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    private final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @PutMapping("/{questionId}")
    public Integer scoreQuestion(@PathVariable Long questionId, @RequestBody QuestionVO questionVO,
                                 HttpServletRequest request){

        String token = request.getHeader("Authorization");
        Teacher teacher = Jwt.unSign(token,Teacher.class);
        
        Question question = new Question(questionVO);
        question.setId(questionId);
        return questionService.scoreQuestion(question,teacher);
    }

}

