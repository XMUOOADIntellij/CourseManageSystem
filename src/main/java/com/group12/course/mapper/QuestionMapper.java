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

    /**
     * 插入提问记录
     * @param record 提问记录
     * @return 1成功 0失败
     */
    Integer insertQuestion(Question record);

    /**
     * 通过id查找提问
     * @param id id
     * @return 提问记录
     */
    Question selectQuestionById(Long id);

    /**
     * 更新提问记录
     * @param record 提问记录
     * @return 1成功 0失败
     */
    Integer updateQuestion(Question record);

    /**
     * 通过班级讨论课删除提问记录
     * @param klassSeminarId 班级讨论课id
     * @return 删除的记录数
     */
    Integer deleteQuestionByKlassSeminarId(Long klassSeminarId);

    /**
     * 列出班级讨论课中的提问
     * @param klassSemiarId 班级讨论课id
     * @return 提问列表
     */
    List<Question> listQuestionByKlassSeminarId(Long klassSemiarId);

    /**
     * 列出当前班级讨论课某一展示的提问
     * @param klassSeminarId 班级讨论课id
     * @param attendanceId 报名展示id
     * @return 提问列表
     */
    List<Question> listQuestionByKlassSeminarIdAndAttendanceId(Long klassSeminarId,Long attendanceId);

    /**
     * 获得某小组在一次班级讨论课的提问
     * @param klassSeminarId 班级讨论课id
     * @param teamId 队伍id
     * @return 问题list
     */
    List<Question> listQuestionByKlassSeminarIdAndTeamId(Long klassSeminarId,Long teamId);

    /**
     * 获得某次
     * @return
     */
    Question selectUnselectQuestionByAttendanceIdAndStudentId(Long attendanceId,Long studentId);
}