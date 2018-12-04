package com.group12.course.mapper;

import com.group12.course.entity.Group;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Group Mapper 接口
 * @author Tan Xue
 * 2018年12月1日
 */
@Mapper
@Component
public interface GroupMapper {

    /**
     * 获取当前课程的所有小组
     * @param courseId
     * @return
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
     * @return Group 对象
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