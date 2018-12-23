package com.group12.course.controller;

import com.group12.course.controller.vo.SeminarVO;
import com.group12.course.entity.Student;
import com.group12.course.entity.Teacher;
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
 *
 * @author Y Jiang
 * @date 2018/12/23
 */

@Controller
public class SeminarProgressController {

    private static final Logger logger = LoggerFactory.getLogger(SeminarProgressController.class);

    @Autowired
    SeminarService seminarService;

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

        return null;
    }
}
