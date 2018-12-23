package com.group12.course.service;

import com.group12.course.dao.MemberLimitStrategyDao;
import com.group12.course.dao.TeamStrategyDao;
import com.group12.course.entity.strategy.MemberLimitStrategy;
import com.group12.course.entity.strategy.TeamStrategy;
import com.group12.course.mapper.MemberLimitStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberLimitStrategyService {

    @Autowired
    MemberLimitStrategyDao memberLimitStrategyDao;
    @Autowired
    TeamStrategyDao teamStrategyDao;

    public MemberLimitStrategy selectMemberLimitStrategyById(Long id){
        return memberLimitStrategyDao.selectMemberLimitStrategyById(id);
    }

    public int deleteMemberLimitStrategy(Long courseId){

        MemberLimitStrategy memberLimitStrategy = selectMemberLimitStrategyByCourseId(courseId);
        return memberLimitStrategyDao.deleteMemberLimitStrategy(memberLimitStrategy.getId());
    }

    public int addMemberLimitStrategy(Long courseId,MemberLimitStrategy record){
        return memberLimitStrategyDao.addMemberLimitStrategy(courseId,record);
    }

    public int updateMemberLimitStrategy(MemberLimitStrategy record){
        return memberLimitStrategyDao.updateMemberLimitStrategy(record);
    }

    /**
     * 根据课程id 查找组队人数限制策略
     * @param courseId
     * @return
     */
    public MemberLimitStrategy selectMemberLimitStrategyByCourseId(Long courseId){
        //从team_strategy中找到相应课程的组队人数限制策略记录
        TeamStrategy teamStrategy = teamStrategyDao.selectTeamStrategyByCourseIdAndStrategyName(courseId,new String("member_limit_strategy"));
        //根据组队人数限制策略id 查找策略
        MemberLimitStrategy memberLimitStrategy = memberLimitStrategyDao.selectMemberLimitStrategyById(teamStrategy.getStrategy().getId());

        return memberLimitStrategy;
    }
}
