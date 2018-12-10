package com.group12.course.mapper;

import com.group12.course.entity.Class;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class Mapper接口
 * @author Tan Xue
 * @date 2018/11/30
 */

@Mapper
@Component
public interface ClassMapper {

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