package com.group12.course.mapper;

import com.group12.course.entity.SeminarScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 讨论课分数 mapper
 * @author Y Jiang
 * @date  2018/12/22
 */
@Component
@Mapper
public interface SeminarScoreMapper {

    /**
     * 通过班级讨论课id删除
     * @param klassSeminarId 班级讨论课id
     * @return 1成功 0失败
     */
    Integer deleteSeminarScoreByKlassSeminarId(Long klassSeminarId);

    /**
     * 更新班级讨论课分数
     * @param seminarScore 讨论课分数记录
     * @return 1成功 0失败
     */
    Integer updateSeminarScore(SeminarScore seminarScore);

    /**
     * 插入讨论课分数列表
     * @param seminarScoreList 讨论课分数列表记录
     * @return 成功数目
     */
    Integer insertSeminarScoreList(List<SeminarScore> seminarScoreList);

    /**
     * 查询某一小组在某班级讨论课的分数
     * @param klassSeminarId 班级讨论课id
     * @param teamId 队伍id
     * @return 讨论课分数记录
     */
    SeminarScore selectSeminarScoreByKlassSeminarIdAndTeamId(Long klassSeminarId,Long teamId);

    /**
     * 根据班级讨论课id列出所有讨论课分数
     * @param klassSeminarId 班级讨论课id
     * @return 讨论课分数列表
     */
    List<SeminarScore> listSeminarScoreByKlassSeminarId(Long klassSeminarId);

    /**
     * 列出小组在各个班级讨论课的分数
     * @param klassSeminarIdList 班级讨论课id列表
     * @param teamId 队伍id
     * @return 讨论课分数列表
     */
    List<SeminarScore> listSeminarScoreByKlassSeminarIdListAndTeamId(@Param("klassSeminarIdList") List<Long> klassSeminarIdList, @Param("teamId") Long teamId);
}
