package com.group12.course.dao;

import com.group12.course.entity.Seminar;

/**
 * Seminar Dao层接口
 * @author Y Jiang
 * @date 2018/12/1
 */
public interface SeminarDao {

    /**
     * 通过讨论课Id删除讨论课
     * @param id Long
     * @return int
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入一个讨论课
     * @param record Seminar
     * @return int
     */
    int insert(Seminar record);

    /**
     * 插入讨论课，对应字段
     * @param record Seminar
     * @return int
     */
    int insertSelective(Seminar record);

    /**
     * 通过讨论课Id获取讨论课
     * @param id Long
     * @return Seminar
     */
    Seminar selectByPrimaryKey(Long id);

    /**
     * 通过讨论课Id更新讨论课对应字段
     * @param record Seminar
     * @return int
     */
    int updateByPrimaryKeySelective(Seminar record);

    /**
     * 通过讨论课Id更新讨论课
     * @param record Seminar
     * @return int
     */
    int updateByPrimaryKey(Seminar record);
}
