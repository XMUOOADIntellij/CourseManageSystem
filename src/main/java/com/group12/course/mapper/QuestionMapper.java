package com.group12.course.mapper;

import com.group12.course.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 讨论课提问的Mapper
 * @author Y Jiang
 * @date 2018/12/12
 */
@Mapper
@Component
public interface QuestionMapper {

    Integer delete(Long id);

    Integer insert(Question record);

    Question select(Long id);

    Integer update(Question record);

    Integer deleteQuestionByKlassSeminarId(Long klassSeminarId);

}