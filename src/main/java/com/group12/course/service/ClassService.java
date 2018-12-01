package com.group12.course.service;


import com.group12.course.entity.Class;

import java.util.List;

/**
 * @author Tan Xue
 * 2018年11月30日
 * ClassService 接口
 * 获得所有班级
 * 根据获取班级
 * 增加班级
 * 删除班级
 * 更新班级
 */
public interface ClassService {
    /**
     * 获得所有班级
     * @return List<Class>
     */
    List<Class> listClasses();

    /**
     * 根据班级id获得班级
     * @param id int
     * @return Class
     */
    Class getClassByClassId(int id);

    /**
     * 增加班级
     * @param entity Class
     * @return Class
     */
    boolean addClass(Class entity);

    /**
     * 删除班级
     * @param id int
     * @return Class
     */
    boolean deleteClass(int id);

    /**
     * 更新班级
     * @param entity Class
     * @return Class
     */
    boolean updateClass(Class entity);
}
