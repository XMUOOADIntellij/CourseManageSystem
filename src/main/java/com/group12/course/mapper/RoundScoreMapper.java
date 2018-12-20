package com.group12.course.mapper;

import com.group12.course.entity.RoundScore;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface RoundScoreMapper {

    public Integer updateRoundScore(RoundScore roundScore);
}
