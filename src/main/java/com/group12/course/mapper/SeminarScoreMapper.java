package com.group12.course.mapper;

import com.group12.course.entity.SeminarScore;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface SeminarScoreMapper {

    SeminarScore selectSeminarScoreByKlassSeminarIdAndTeamId(Long klassSeminarId,Long teamId);

    Integer deleteSeminarScoreByKlassSeminarId(Long klassSeminarId);

    Integer updateSeminarScore(SeminarScore seminarScore);

    Integer insertSeminarScoreList(List<SeminarScore> seminarScoreList);
}
