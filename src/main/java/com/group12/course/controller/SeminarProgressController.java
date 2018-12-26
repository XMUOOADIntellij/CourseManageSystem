package com.group12.course.controller;

import com.group12.course.controller.vo.AttendanceVO;
import com.group12.course.controller.vo.QuestionVO;
import com.group12.course.controller.vo.SeminarVO;
import com.group12.course.entity.*;
import com.group12.course.service.AttendanceService;
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

/**
 * 讨论课进程 controller
 * 异步 WebSocket
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
    @Autowired
    AttendanceService attendanceService;


    /**
     * 开始班级讨论课
     * @param seminarId 讨论课id
     * @param classId 班级id
     * @param message 消息获得老师
     * @return seminar
     */
    @MessageMapping(value = "/Socket/seminar/{seminarId}/class/{classId}/start")
    @SendTo("/seminarSocket/progress")
    public SeminarVO startSeminar(@DestinationVariable Long seminarId, @DestinationVariable Long classId,
                                 Message message){

        Teacher teacher = new Teacher();
        teacher.setId(1L);
        return new SeminarVO(seminarService.startSeminar(teacher,seminarId,classId));
    }

    /**
     * 处理发往 /app/seminar/pause的消息
     * 暂停讨论课
     * @return SeminarVO
     */
    @MessageMapping("/Socket/seminar/{seminarId}/class/{classId}/pause")
    @SendTo("/seminarSocket/progress")
    public SeminarVO pauseSeminar(Message message, @DestinationVariable Long seminarId,
                                  @DestinationVariable Long classId) {

        Teacher teacher = new Teacher();
        teacher.setId(1L);

        return new SeminarVO(seminarService.pauseSeminar(teacher,seminarId,classId));
    }

    @MessageMapping("/Socket/seminar/{seminarId}/class/{classId}/end")
    @SendTo("/seminarSocket/progress")
    public SeminarVO endSeminar(Message message, @DestinationVariable Long seminarId,
                                  @DestinationVariable Long classId) {

        Teacher teacher = new Teacher();
        teacher.setId(1L);

        return new SeminarVO(seminarService.endSeminar(teacher,seminarId,classId));
    }

    /**
     * 学生提问
     * @param seminarId 讨论课id
     * @param classId 班级id
     * @param message 消息
     * @param questionVO 提问体
     * @return
     */
    @MessageMapping("/Socket/course/courseId/seminar/{seminarId}/class/{classId}/question")
    @SendTo("/seminarSocket/question")
    public QuestionVO askQuestion(@DestinationVariable Long seminarId, @DestinationVariable Long classId,
                                  @DestinationVariable Long courseId, Message message,QuestionVO questionVO){

        Student student = new Student();
        student.setId(1L);

        Question question = questionService.askQuestion(courseId,seminarId,classId,new Question(questionVO),student);
        return new QuestionVO(question);
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
        Teacher teacher = new Teacher();
        teacher.setId(1L);

        return new QuestionVO(questionService.answerQuestion(
                teacher,seminarId,classId,attendanceId));
    }


    /**
     * 选取下一组
     * @param seminarId 讨论课id
     * @param classId 班级id
     * @param attendanceId 当前的展示id
     * @param message 消息体，jwt
     * @return 下一组展示
     */
    @MessageMapping("/Socket/seminar/{seminarId}/class/{classId}/attendance/{attendanceId}/next")
    @SendTo("/seminarSocket/attendance")
    public AttendanceVO nextAttendance(@DestinationVariable Long seminarId, @DestinationVariable Long classId,
                                       @DestinationVariable Long attendanceId, Message message){

        Teacher teacher = new Teacher();
        teacher.setId(1L);

        return new AttendanceVO(attendanceService.nextAttendance(seminarId,classId,attendanceId));
    }



}
