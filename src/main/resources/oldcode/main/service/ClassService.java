package com.group12.course.service;

import com.group12.course.entity.Class;

import java.util.List;

/**
 * Class service层接口
 * @author Tan Xue
 * @date 2018/11/30
 */

public interface ClassService {

    /**
     * 找到当前课程的所有班级
     * @param courseId String
     * @return List  班级列表
     */
    List<Class> getAllClasses(Long courseId);

    /**
     * 通过班级id获取班级
     * @param id 班级号
     * @return Class对象
     */
    Class getClassById(String id);

    /**
     * 添加班级
     * @param record 班级
     * @return 添加的班级数
     */
    int addClass(Class record);

    /**
     * 修改班级
     * @param record 班级
     * @return 修改个数
     */
    int updateClass(Class record);

    /**
     * 通过班级id删除班级
     * @param id 班级号
     * @return 删除个数
     */
    int deleteClass(String id);
}
