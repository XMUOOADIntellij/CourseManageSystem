package com.group12.course.mapper;

import com.group12.course.entity.strategy.MemberLimitStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author Tan Xue
 */
@Mapper
@Component
public interface MemberLimitStrategyMapper {

    /**
     * 查找
     * @param id
     * @return
     */
    MemberLimitStrategy selectMemberLimitStrategyById(Long id);

    /**
     *  删除
     * @param id
     * @return
     */
    int deleteMemberLimitStrategy(Long id);

    /**
     *  添加
     * @param record
     * @return
     */
    int addMemberLimitStrategy(MemberLimitStrategy record);

    /**
     * 修改
     * @param record
     * @return
     */
    int updateMemberLimitStrategy(MemberLimitStrategy record);
}
