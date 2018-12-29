package com.group12.course.dao;

import com.group12.course.entity.Team;
import com.group12.course.entity.strategy.MemberLimitStrategy;
import com.group12.course.entity.strategy.Strategy;
import com.group12.course.entity.strategy.TeamAndStrategy;
import com.group12.course.mapper.TeamAndStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tan Xue
 */
@Component
public class TeamAndStrategyDao {

    @Autowired
    TeamAndStrategyMapper teamAndStrategyMapper;

    @Autowired
    MemberLimitStrategyDao memberLimitStrategyDao;

    @Autowired
    ConflictCourseStrategyDao conflictCourseStrategyDao;

    @Autowired
    CourseMemberLimitStrategyDao courseMemberLimitStrategyDao;

    @Autowired
    TeamOrStrategyDao teamOrStrategyDao;

    public List<TeamAndStrategy> selectTeamAndStrategyById(Long id){
        return teamAndStrategyMapper.selectTeamAndStrategyById(id);
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
        List<TeamAndStrategy> teamAndStrategies = teamAndStrategyMapper.selectTeamAndStrategyById(strategyId);
        if (teamAndStrategies==null||teamAndStrategies.isEmpty()){
            return true;
        }
        List<Boolean> booleans=new ArrayList<>(teamAndStrategies.size());

        for (TeamAndStrategy teamAndStrategy:teamAndStrategies) {
            Boolean status=false;
            List<Strategy> strategies = teamAndStrategy.getStrategyList();
            for (Strategy strategy: strategies) {
                switch (strategy.getStrategyType()){
                    case "MemberLimitStrategy":
                        status = memberLimitStrategyDao.judgeTeam(strategy.getId(),team);
                        break;
                    case "TeamOrStrategy":
                        status = teamOrStrategyDao.judgeTeam(strategy.getId(),team);
                        break;
                    case "ConflictCourseStrategy":
                        status = conflictCourseStrategyDao.judgeTeam(strategy.getId(),team);
                        break;
                    case "CourseMemberLimitStrategy":
                        status = courseMemberLimitStrategyDao.judgeTeam(strategy.getId(),team);
                        break;
                    case "TeamAndStrategy":
                        status = judgeTeam(strategy.getId(),team);
                        break;
                    default:
                        // 默认不存在的时候默认为对的了（不影响其余的）
                        status=true;
                        break;
                }
                // 只要某条不符合，整体就不符合，不必继续判断
                if (!status){
                    break;
                }
            }
            if (!status){
                break;
            }
            booleans.add(status);
        }
        for (Boolean eachJudge:booleans) {
            // 只要有一个不符合就不符合要求
            if (!eachJudge){
                return false;
            }
        }
        return true;
    }

    /**
     * 计算下一条要插入的记录的id
     * @return
     */
    public Long calculateId(){
        Long maxId = new Long(0) ;
        List<TeamAndStrategy> teamAndStrategyList = teamAndStrategyMapper.selectAllTeamAndStrategy();
        if(teamAndStrategyList!=null && !teamAndStrategyList.isEmpty()) {
            for (TeamAndStrategy teamAndStrategy : teamAndStrategyList) {
                if (teamAndStrategy.getId() > maxId) {
                    maxId = teamAndStrategy.getId();
                }
            }
        }
        return maxId + 1;
    }

    /**
     * 添加与策略列表
     * @param teamAndStrategyList
     * @return 新添加的或策略的id
     */
    public List<TeamAndStrategy> addTeamAndStrategyList(List<TeamAndStrategy> teamAndStrategyList){
        Long teamAndStrategyId = calculateId();
        for (TeamAndStrategy teamAndStrategy:teamAndStrategyList) {
            teamAndStrategyMapper.addTeamAndStrategy(teamAndStrategyId,teamAndStrategy.getStrategyName(),teamAndStrategy.getStrategyList().get(0).getId());
        }
        return selectTeamAndStrategyById(teamAndStrategyId);
    }

}
