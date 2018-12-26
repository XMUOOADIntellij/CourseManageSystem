package com.group12.course.dao;

import com.group12.course.entity.Team;
import com.group12.course.entity.strategy.MemberLimitStrategy;
import com.group12.course.entity.strategy.TeamAndStrategy;
import com.group12.course.mapper.TeamAndStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public int addTeamAndStrategy(TeamAndStrategy teamAndStrategy){
        return teamAndStrategyMapper.addTeamAndStrategy(teamAndStrategy);
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
            switch (teamAndStrategy.getStrategyName()){
                case "MemberLimitStrategy":
                    status = memberLimitStrategyDao.judgeTeam(teamAndStrategy.getStrategy().getId(),team);
                    break;
                case "TeamOrStrategy":
                    status = teamOrStrategyDao.judgeTeam(teamAndStrategy.getStrategy().getId(),team);
                    break;
                case "ConflictCourseStrategy":
                    status = conflictCourseStrategyDao.judgeTeam(teamAndStrategy.getStrategy().getId(),team);
                    break;
                case "CourseMemberLimitStrategy":
                    status = courseMemberLimitStrategyDao.judgeTeam(teamAndStrategy.getStrategy().getId(),team);
                    break;
                case "TeamAndStrategy":
                    status = judgeTeam(teamAndStrategy.getStrategy().getId(),team);
                    break;
                default:
                    // 默认不存在的时候默认为对的了（不影响其余的）
                    status=true;
                    break;
            }
            booleans.add(status);
        }
        for (Boolean eachJudge:booleans) {
            if (!eachJudge){
                return false;
            }
        }
        return true;
    }
}
