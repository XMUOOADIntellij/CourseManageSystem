package com.group12.course.dao;

import com.group12.course.entity.Team;
import com.group12.course.entity.strategy.TeamAndStrategy;
import com.group12.course.entity.strategy.TeamOrStrategy;
import com.group12.course.mapper.TeamOrStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public int addTeamOrStrategy(TeamOrStrategy teamOrStrategy){
        return teamOrStrategyMapper.addTeamOrStrategy(teamOrStrategy);
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
            switch (teamOrStrategy.getStrategyName()){
                case "MemberLimitStrategy":status = memberLimitStrategyDao.judgeTeam(teamOrStrategy.getStrategy().getId(),team);break;
                case "TeamOrStrategy":status = judgeTeam(teamOrStrategy.getStrategy().getId(),team);break;
                case "ConflictCourseStrategy":status = conflictCourseStrategyDao.judgeTeam(teamOrStrategy.getStrategy().getId(),team);break;
                case "CourseMemberLimitStrategy":status = courseMemberLimitStrategyDao.judgeTeam(teamOrStrategy.getStrategy().getId(),team);break;
                case "TeamAndStrategy":status = teamAndStrategyDao.judgeTeam(teamOrStrategy.getStrategy().getId(),team);break;
                default:
                    // 默认不存在的时候默认为错的了（不影响其余的）
                    status=false;
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
}
