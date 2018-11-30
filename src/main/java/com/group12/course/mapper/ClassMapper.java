package com.group12.course.mapper;

import com.group12.course.entity.Class;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tan Xue
 * 2018年11月30日
 * Class Mapper 层接口
 * 获得所有班级
 * 根据id获取班级
 * 增加班级
 * 删除班级
 * 更新班级
 */
@Mapper
@Component
public interface ClassMapper {
    /**
     * 获得所有班级
     * @return List<Class>
     */
    List<Class> getAllClasses();

    /**
     * 根据课程id获得班级
     * @param id int
     * @return Class
     */
    Class getClassNameById(@Param("id") int id);

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
