package com.group12.course.dao;

import com.group12.course.entity.Group;

import java.util.List;

/**
 * Group Dao 层接口
 * @author Tan Xue
 * @date 2018/12/1
 */
public interface GroupDao {

    /**
     * 获取当前课程的所有小组
     * @param courseId 课程号
     * @return 小组列表
     */
    List<Group> listGroups(Long courseId);

    /**
     * 通过小组id 删除小组
     * @param id 小组编号
     * @return 删除个数
     */
    int deleteByPrimaryKey(String id);

    /**
     * 新建小组
     * @param record 小组
     * @return 新建个数
     */
    int insert(Group record);

    /**
     * 新建小组，只插入不为 null 的字段
     * @param record 小组
     * @return 新建个数
     */
    int insertSelective(Group record);

    /**
     * 通过小组id获取小组
     * @param id 小组编号
     * @return Team 对象
     */
    Group selectByPrimaryKey(String id);

    /**
     * 修改小组对应字段
     * @param record 小组
     * @return 修改个数
     */
    int updateByPrimaryKeySelective(Group record);

    /**
     * 修改小组
     * @param record 小组
     * @return 修改个数
     */
    int updateByPrimaryKey(Group record);

}
