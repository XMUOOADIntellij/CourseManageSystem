package com.group12.course.mapper;

import com.group12.course.entity.Round;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Round Mapper 层接口
 * @author Tan Xue
 * @date 2018/12/12
 */

@Mapper
@Component
public interface RoundMapper {

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteRound(Long id);

    /**
     *  添加
     * @param record
     * @return
     */
    int addRound(Round record);

    /**
     *  选择
     * @param id
     * @return
     */
    Round selectRoundById(Long id);

    /**
     * 修改
     * @param record
     * @return
     */
    int updateRound(Round record);

    /**
     *  选择
     * @param id
     * @return
     */
    List<Round> selectRoundByCourseId(Long id);
}