package com.group12.course.dao;

import com.group12.course.entity.Class;

import java.util.List;

/**
 * Class Dao层接口
 * @author Tan Xue
 * 2018年11月30
 */
public interface ClassDao {

    /**
     * 找到当前课程的所有班级
     * @param courseId String
     * @return List  班级列表
     */
    List<Class> listClasses(Long courseId);

    /**
     * 通过班级id删除班级
     * @param id 班级号
     * @return 删除个数
     */
    int deleteByPrimaryKey(String id);

    /**
     * 添加班级
     * @param record 班级
     * @return 添加的班级数
     */
    int insert(Class record);

    /**
     * 添加班级，只插入不为null的字段，不会影响有默认值的字段
     * @param record 班级
     * @return 添加的班级数
     */
    int insertSelective(Class record);

    /**
     * 通过班级id获取班级
     * @param id 班级号
     * @return Class对象
     */
    Class selectByPrimaryKey(String id);

    /**
     * 修改班级对应字段
     * @param record 班级
     * @return 修改个数
     */
    int updateByPrimaryKeySelective(Class record);

    /**
     * 修改班级
     * @param record 班级
     * @return 修改个数
     */
    int updateByPrimaryKey(Class record);
}
