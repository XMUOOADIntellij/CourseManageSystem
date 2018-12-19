package com.group12.course.mapper;

import com.group12.course.entity.Round;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Round Mapper 层接口
 * @author Tan Xue
 * @date 2018/12/12
 */

@Mapper
@Component
public interface RoundMapper {

    int deleteRound(Long id);

    int addRound(Round record,Long courseId);

    Round selectRoundById(Long id);

    int updateRound(Round record);

    List<Round> selectRoundByCourseId(Long id);
}