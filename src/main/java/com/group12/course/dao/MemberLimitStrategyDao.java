package com.group12.course.dao;

import com.group12.course.entity.Course;
import com.group12.course.entity.strategy.MemberLimitStrategy;
import com.group12.course.entity.strategy.TeamStrategy;
import com.group12.course.mapper.MemberLimitStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public int addMemberLimitStrategy(Long courseId,MemberLimitStrategy record){
        int status1 = memberLimitStrategyMapper.addMemberLimitStrategy(record);

        Course course = courseDao.getCourse(courseId);
        TeamStrategy teamStrategy = new TeamStrategy();
        teamStrategy.setCourse(course);
        teamStrategy.setStrategy(record);
        teamStrategy.setStrategyName(startegyName);
        int status2 = teamStrategyDao.addTeamStrategy(teamStrategy);

        if(status1 !=0 && status2 != 0){
            return 1;
        }
        else{
            return 0;
        }
    }

    public int updateMemberLimitStrategy(MemberLimitStrategy record){
        return memberLimitStrategyMapper.updateMemberLimitStrategy(record);
    }
}
