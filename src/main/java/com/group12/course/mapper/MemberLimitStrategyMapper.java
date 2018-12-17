package com.group12.course.mapper;

import com.group12.course.entity.strategy.MemberLimitStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface MemberLimitStrategyMapper {

    MemberLimitStrategy selectMemberLimitStrategyById(Long id);

    int deleteMemberLimitStrategy(Long id);

    int addMemberLimitStrategy(MemberLimitStrategy record);

    int updateMemberLimitStrategy(MemberLimitStrategy record);
}
