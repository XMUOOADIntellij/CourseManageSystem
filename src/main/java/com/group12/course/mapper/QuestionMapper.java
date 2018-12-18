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

    List<Question> selectQuestionByKlassSeminarId(Long klassSemiarId);

}