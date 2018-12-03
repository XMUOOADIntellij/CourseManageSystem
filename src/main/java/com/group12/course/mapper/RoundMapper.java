package com.group12.course.mapper;

import com.group12.course.entity.Round;

public interface RoundMapper {
    int deleteByPrimaryKey(Long roundId);

    int insert(Round record);

    int insertSelective(Round record);

    Round selectByPrimaryKey(Long roundId);

    int updateByPrimaryKeySelective(Round record);

    int updateByPrimaryKey(Round record);
}