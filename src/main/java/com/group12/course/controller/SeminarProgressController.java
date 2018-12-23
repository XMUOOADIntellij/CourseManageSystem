package com.group12.course.controller;

import com.group12.course.controller.vo.QuestionVO;
import com.group12.course.controller.vo.SeminarVO;
import com.group12.course.entity.Question;
import com.group12.course.entity.Student;
import com.group12.course.entity.Teacher;
import com.group12.course.service.QuestionService;
import com.group12.course.service.SeminarService;
import com.group12.course.tools.Jwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 讨论课进程 controller
 * 异步 WebSocket
 *
 * @author Y Jiang
 * @date 2018/12/23
 */

@Controller
public class SeminarProgressController {

    private static final Logger logger = LoggerFactory.getLogger(SeminarProgressController.class);

    @Autowired
    SeminarService seminarService;
    @Autowired
    QuestionService questionService;

    /**
     * 处理发往 /app/seminar/pause的消息
     * 暂停讨论课
     * @return SeminarVO
     */
    @MessageMapping("/Socket/seminar/{seminarId}/class/{classId}/pause")
    @SendTo("/seminarSocket/progress")
    public SeminarVO pauseSeminar(Message message, @DestinationVariable Long seminarId, @DestinationVariable Long classId) {

        String token = message.getHeaders().get("Authorization").toString();
        Teacher teacher = Jwt.unSign(token, Teacher.class);

        return new SeminarVO(seminarService.pauseSeminar(teacher,seminarId,classId));
    }

    /**
     * 学生提问
     * @param seminarId 讨论课id
     * @param classId 班级id
     * @param message 消息
     * @param questionVO 提问体
     * @return
     */
    @MessageMapping("/Socket/seminar/{seminarId}/class/{classId}/question")
    @SendTo("/seminarSocket/question")
    public QuestionVO askQuestion(@DestinationVariable Long seminarId, @DestinationVariable Long classId,
                                  Message message,QuestionVO questionVO){

        String token = message.getHeaders().get("Authorization").toString();
        Student student = Jwt.unSign(token, Student.class);

        return new QuestionVO(questionService.askQuestion(seminarId,classId,new Question(questionVO),student));
    }


    /**
     * 老师选取问题
     * @param seminarId 讨论课id
     * @param classId 班级id
     * @param attendanceId 被提问的展示id
     * @param message 消息，jwt
     * @return 提问
     */
    @MessageMapping("/Socket/seminar/{seminarId}/class/{classId}/attendance/{attendanceId}/question")
    @SendTo("/seminarSocket/question")
    public QuestionVO answerQuestion(@DestinationVariable Long seminarId,@DestinationVariable Long classId,
                                     @DestinationVariable Long attendanceId,Message message){
        String token = message.getHeaders().get("Authorization").toString();
        Teacher teacher = Jwt.unSign(token, Teacher.class);

        return new QuestionVO(questionService.answerQuestion(
                teacher,seminarId,classId,attendanceId));
    }


}
