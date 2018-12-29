package com.group12.course.dao;

import com.group12.course.entity.Course;
import com.group12.course.entity.Student;
import com.group12.course.entity.Team;
import com.group12.course.entity.strategy.MemberLimitStrategy;
import com.group12.course.entity.strategy.TeamStrategy;
import com.group12.course.mapper.MemberLimitStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tan Xue
 */

@Component
public class MemberLimitStrategyDao {

    @Autowired
    MemberLimitStrategyMapper memberLimitStrategyMapper;
    @Autowired
    CourseDao courseDao;
    @Autowired
    TeamStrategyDao teamStrategyDao;

    private final  String startegyName="member_limit_strategy";

    public MemberLimitStrategy selectMemberLimitStrategyById(Long id){
        return memberLimitStrategyMapper.selectMemberLimitStrategyById(id);
    }

    public int deleteMemberLimitStrategy(Long id){
        return memberLimitStrategyMapper.deleteMemberLimitStrategy(id);
    }

    public int addMemberLimitStrategy(MemberLimitStrategy record){
        return memberLimitStrategyMapper.addMemberLimitStrategy(record);

    }

    public int updateMemberLimitStrategy(MemberLimitStrategy record){
        return memberLimitStrategyMapper.updateMemberLimitStrategy(record);
    }

    /**
     * 根据传入的 id 和 队伍
     * 判断该队伍是否符合该 id 的策略
     *
     * @param strategyId 策略 id
     * @param team 待判断的队伍
     * @return 是否符合
     */
    public Boolean judgeTeam(Long strategyId,Team team){
        MemberLimitStrategy memberLimitStrategy = memberLimitStrategyMapper.selectMemberLimitStrategyById(strategyId);
        int minMember = memberLimitStrategy.getMinMember();
        int maxMember = memberLimitStrategy.getMaxMember();
        int memberCount = 0;
        Student leader = team.getLeader();
        List<Student> member = team.getMembers();
        if (member==null||member.isEmpty()){
            memberCount=1;
        }
        else {
            memberCount=member.size()+1;
        }
        if (memberCount<minMember){
            return false;
        }
        else if (memberCount>maxMember){
            return false;
        }
        else {
            return true;
        }
    }
}
