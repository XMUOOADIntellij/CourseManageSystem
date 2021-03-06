package com.group12.course.dao;

import com.group12.course.entity.Team;
import com.group12.course.entity.strategy.TeamAndStrategy;
import com.group12.course.entity.strategy.Strategy;
import com.group12.course.entity.strategy.TeamOrStrategy;
import com.group12.course.mapper.TeamOrStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tan Xue
 */
@Component
public class TeamOrStrategyDao {

    @Autowired
    TeamOrStrategyMapper teamOrStrategyMapper;

    @Autowired
    MemberLimitStrategyDao memberLimitStrategyDao;

    @Autowired
    ConflictCourseStrategyDao conflictCourseStrategyDao;

    @Autowired
    CourseMemberLimitStrategyDao courseMemberLimitStrategyDao;

    @Autowired
    TeamAndStrategyDao teamAndStrategyDao;

    public List<TeamOrStrategy> selectTeamOrStrategyById(Long id){
        return teamOrStrategyMapper.selectTeamOrStrategyById(id);
    }


    /**
     * 根据传入的 id 和 队伍
     * 判断该队伍是否符合该 id 的策略
     *
     * @param strategyId 策略 id
     * @param team 待判断的队伍
     * @return 是否符合
     */
    public Boolean judgeTeam(Long strategyId , Team team){
        List<TeamOrStrategy> teamOrStrategies = teamOrStrategyMapper.selectTeamOrStrategyById(strategyId);
        if (teamOrStrategies==null||teamOrStrategies.isEmpty()){
            return true;
        }
        List<Boolean> booleans=new ArrayList<>(teamOrStrategies.size());
        for (TeamOrStrategy teamOrStrategy:teamOrStrategies) {
            Boolean status=false;
            List<Strategy> strategies = teamOrStrategy.getStrategyList();
            for (Strategy strategy:strategies) {
                switch (strategy.getStrategyType()){
                    case "MemberLimitStrategy":
                        status = memberLimitStrategyDao.judgeTeam(strategy.getId(),team);
                        break;
                    case "TeamOrStrategy":
                        status = judgeTeam(strategy.getId(),team);
                        break;
                    case "ConflictCourseStrategy":
                        status = conflictCourseStrategyDao.judgeTeam(strategy.getId(),team);
                        break;
                    case "CourseMemberLimitStrategy":
                        status = courseMemberLimitStrategyDao.judgeTeam(strategy.getId(),team);
                        break;
                    case "TeamAndStrategy":
                        status = teamAndStrategyDao.judgeTeam(strategy.getId(),team);
                        break;
                    default:
                        // 默认不存在的时候默认为错的了（不影响其余的）
                        status=false;
                        break;
                }
                // 只要某条符合，全体就符合了，不必继续判断
                if (status){
                    break;
                }
            }
            if (status){
                break;
            }
            booleans.add(status);
        }
        for (Boolean eachJudge:booleans) {
            if (eachJudge){
                return true;
            }
        }
        return false;
    }


    /**
     * 计算下一条要插入的记录的id
     * @return
     */
    public Long calculateId(){
        Long maxId = new Long(0) ;
        List<TeamOrStrategy> teamOrStrategyList = teamOrStrategyMapper.selectAllTeamOrStrategy();
        if(teamOrStrategyList!=null && !teamOrStrategyList.isEmpty()) {
            for (TeamOrStrategy teamOrStrategy : teamOrStrategyList) {
                if (teamOrStrategy.getId() > maxId) {
                    maxId = teamOrStrategy.getId();
                }
            }
        }
        return maxId + 1;
    }

    /**
     * 添加或策略列表
     * @param teamOrStrategyList
     * @return 新添加的或策略的id
     */
    public List<TeamOrStrategy> addTeamOrStrategyList(List<TeamOrStrategy> teamOrStrategyList){
        Long teamOrStrategyId = calculateId();
        for (TeamOrStrategy teamOrStrategy:teamOrStrategyList) {
            teamOrStrategyMapper.addTeamOrStrategy(teamOrStrategyId,teamOrStrategy.getStrategyName(),teamOrStrategy.getStrategyList().get(0).getId());
        }
        return teamOrStrategyMapper.selectTeamOrStrategyById(teamOrStrategyId);
    }
}
