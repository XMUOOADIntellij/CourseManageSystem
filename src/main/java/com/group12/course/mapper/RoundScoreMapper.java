package com.group12.course.mapper;

import com.group12.course.entity.Round;
import com.group12.course.entity.RoundScore;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RoundScoreMapper {

    Integer updateRoundScore(RoundScore roundScore);

    RoundScore selectRoundScoreByRoundIdAndTeamId(Long roundId,Long teamId);

    List<RoundScore> listRoundScoreByRoundId(Long rounId);

    List<RoundScore> listRoundScoreByTeamId(Long teamId);
}
