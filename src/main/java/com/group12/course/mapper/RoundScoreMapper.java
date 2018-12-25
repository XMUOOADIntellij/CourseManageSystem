package com.group12.course.mapper;

import com.group12.course.entity.RoundScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 轮次分数mapper
 * @author Y Jiang
 * @date 2018/12/22
 */
@Mapper
@Component
public interface RoundScoreMapper {

    /**
     * 更新轮次分数
     * @param roundScore 轮次分数记录
     * @return 1成功 0失败
     */
    Integer updateRoundScore(RoundScore roundScore);

    /**
     * 插入轮次分数记录
     * @param record 记录
     * @return 1成功 0失败
     */
    Integer insertRoundScore(RoundScore record);

    /**
     * 查找某个轮下某一队伍的分数
     * @param roundId 轮次id
     * @param teamId 队伍id
     * @return 轮次分数
     */
    RoundScore selectRoundScoreByRoundIdAndTeamId(@Param("roundId") Long roundId,@Param("teamId") Long teamId);

    /**
     * 列出该轮次下所有分数
     * @param rounId 轮次id
     * @return 轮次分数列表
     */
    List<RoundScore> listRoundScoreByRoundId(Long rounId);

    /**
     * 列出队伍在所有轮的分数
     * @param teamId 队伍id
     * @return 轮次分数列表
     */
    List<RoundScore> listRoundScoreByTeamId(Long teamId);

    /**
     * 通过轮次列表列出该队伍在各轮的分数
     * @param roundIdList 轮次列表
     * @param teamId 队伍id
     * @return 轮次分数列表
     */
    List<RoundScore> listRoundScoreByRoundIdListAndTeamId(@Param("roundIdList") List<Long> roundIdList, @Param("teamId") Long teamId);

    /**
     * 通过轮次列表列出所有队伍的分数
     * @param roundIdList 轮次列表
     * @return 轮次分数列表
     */
    List<RoundScore> listRoundScoreByRoundIdList(@Param("roundIdList") List<Long> roundIdList);
}