package com.group12.course.dao;

import com.group12.course.entity.Class;

import java.util.List;

/**
 * @author Tan Xue
 * 2018年11月30日
 * Class dao 层对应接口
 * 获得所有班级
 * 根据id获取班级
 * 增加班级
 * 删除班级
 * 更新班级
 */
public interface  ClassDao {

    /**
     * 获得所有班级
     * @return List<Class>
     */
    List<Class> listClasses();

    /**
     * 根据课程id获得班级
     * @param id int
     * @return Class
     */
    Class getClassByClassId(int id);

    /**
     * 增加班级
     * @param entity  Class
     * @return Class
     */
    boolean add(Class entity);

    /**
     * 删除班级
     * @param id int
     * @return Class
     */
    boolean remove(int id);

    /**
     * 更新班级
     * @param entity  Class
     * @return Class
     */
    boolean update(Class entity);
}
