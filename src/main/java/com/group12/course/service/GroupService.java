package com.group12.course.service;

import com.group12.course.entity.Group;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Group Service 层接口
 * @author Tan Xue
 * @date 2018/12/4
 */

public interface GroupService {

    /**
     * 获取当前课程的所有小组
     * @param courseId 课程号
     * @return 小组列表
     */
    List<Group> getAllGroups(Long courseId);

    /**
     * 通过小组id 获取小组
     * @param groupId 小组编号
     * @return 小组
     */
    Group getGroup(String groupId);

    /**
     * 添加成员
     * @param studentId 学生账号
     * @return 1 表示添加成功，0 表示添加失败
     */
    int addMember(String studentId);

    /**
     * 添加组员
     * @param studentId 学生的学号
     * @return 1 表示删除成功，0 表示删除失败
     */
    int deleteMember(String studentId);

    /**
     * 通过小组id解散小组
     * @param groupId
     * @return
     */
    int disbandGroup(String groupId);

    /**
     * 申请组队
     * @param groupId 小组编号
     * @return 1 表示组队成功，0 表示组队失败
     */
    int apply(String groupId);
}
