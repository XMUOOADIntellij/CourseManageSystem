package com.group12.course.dao;

import com.group12.course.entity.strategy.CourseMemberLimitStrategy;
import com.group12.course.entity.strategy.MemberLimitStrategy;
import com.group12.course.mapper.MemberLimitStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberLimitStrategyDao {

    @Autowired
    MemberLimitStrategyMapper memberLimitStrategyMapper;

    MemberLimitStrategy selectMemberLimitStrategyById(Long id){
        return memberLimitStrategyMapper.selectMemberLimitStrategyById(id);
    }

    int deleteMemberLimitStrategy(Long id){
        return memberLimitStrategyMapper.deleteMemberLimitStrategy(id);
    }

    int addMemberLimitStrategy(MemberLimitStrategy record){
        return memberLimitStrategyMapper.addMemberLimitStrategy(record);
    }

    int updateMemberLimitStrategy(MemberLimitStrategy record){
        return memberLimitStrategyMapper.updateMemberLimitStrategy(record);
    }
}
