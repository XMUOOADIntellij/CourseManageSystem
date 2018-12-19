package com.group12.course.mapper;

import com.group12.course.entity.Seminar;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Seminar Mapper层
 * @author Y Jiang
 * @date 2018/12/11
 */

@Component
@Mapper
public interface SeminarMapper {

    /**
     * 由Id删除讨论课
     * @param id Long
     * @return int
     */
    int deleteSeminarById(Long id);

    /**
     * 新建讨论课
     * @param record Seminar
     * @return int
     */
    int insertSeminar(Seminar record);

    /**
     * 根据id获取单个讨论课对象
     * @param id Long
     * @return Seminar
     */
    Seminar selectSeminarById(Long id);

    /**
     * 修改讨论课信息
     * @param record Seminar
     * @return int
     */
    int updateSeminar(Seminar record);

    /**
     * 查找该轮的所有讨论课
     * @param roundId 课程id
     * @return List<Seminar>
     */
    List<Seminar> listSeminarByRoundId(Long roundId);
}