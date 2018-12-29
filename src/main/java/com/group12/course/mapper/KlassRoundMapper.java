package com.group12.course.mapper;

import com.group12.course.entity.KlassRound;
import com.group12.course.entity.Round;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * KlassRound Mapper层
 * @author Tan Xue
 * @date 2018/12/17
 */
@Mapper
@Component
public interface KlassRoundMapper {

    /**
     * 删除班级轮次
     * @param id
     * @return
     */
    int deleteKlassRound(Long id);

    /**
     * 添加班级轮次
     * @param record
     * @return
     */
    int addKlassRound(KlassRound record);

    /**
     * 查询班级轮次
     * @param id
     * @return
     */
    KlassRound selectKlassRoundById(Long id);

    /**
     * 修改班级轮次
     * @param record
     * @return
     */
    int updateKlassRound(KlassRound record);

    /**
     * 根据班级id 和轮次id查找班级轮次记录
     * @param klassId
     * @param roundId
     * @return
     */
    KlassRound selectKlassRoundByKlassIdAndRoundId(Long klassId,Long roundId);

}
