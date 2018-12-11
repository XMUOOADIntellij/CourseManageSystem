package com.group12.course.mapper;

import com.group12.course.entity.Presentation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


/**
 * 讨论课展示相关Mapper
 * @author Y Jiang
 * @date  2018/12/11
 */
@Mapper
@Component
public interface PresentationMapper {

    /**
     * 报名讨论课插入一条记录
     * @param record (小组teamId,展示顺序order)
     * @return int  Presentation的自增Id
     */
    int insertSelective(Presentation record);


    int deleteByPrimaryKey(Long id);

    Presentation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Presentation record);

    int updateByPrimaryKey(Presentation record);
}