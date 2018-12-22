package com.group12.course.mapper;

import com.group12.course.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 讨论课提问的Mapper
 * @author Y Jiang
 * @date 2018/12/12
 */
@Mapper
@Component
public interface QuestionMapper {


    Integer delete(Long id);

    Integer insertQuestion(Question record);

    Question selectQuestionById(Long id);

    Integer updateQuestion(Question record);

    Integer deleteQuestionByKlassSeminarId(Long klassSeminarId);

    List<Question> listQuestionByKlassSeminarId(Long klassSemiarId);

    List<Question> listQuestionByKlassSeminarIdAndAttendanceId(Long klassSeminarId,Long attendanceId);

    /**
     * 获得某小组在一次班级讨论课的提问
     * @param klassSeminarId 班级讨论课id
     * @param teamId 队伍id
     * @return 问题list
     */
    List<Question> listQuestionByKlassSeminarIdAndTeamId(Long klassSeminarId,Long teamId);
}