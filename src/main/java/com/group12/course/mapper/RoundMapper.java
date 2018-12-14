package com.group12.course.mapper;

import com.group12.course.entity.Round;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Round Mapper 层接口
 * @author Tan Xue
 * @date 2018/12/12
 */

@Mapper
@Component
public interface RoundMapper {

    int deleteRound(Long id);

    int addRound(Round record);

    Round selectRoundById(Long id);

    int updateRound(Round record);
}