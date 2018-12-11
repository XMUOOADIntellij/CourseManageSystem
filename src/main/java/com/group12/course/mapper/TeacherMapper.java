package com.group12.course.mapper;

import com.group12.course.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Teacher Mapper 层接口
 * @author Xu Gang
 * @date 2018年12月10日
 */
@Mapper
@Component
public interface TeacherMapper {

    /**
     * 删除数据库中教师的记录
     *
     * @param account 主键代表用户账号
     * @return 代表处理数量
     * */
    int deleteTeacher(Long account);

    /**
     * 在数据库中添加教师的记录
     *
     * @param record 待添加的教师
     * @return 代表处理数量
     * */
    int addTeacher(Teacher record);

    /**
     * 获取数据库中教师的记录
     *
     * @param account 主键代表用户账号
     * @return 代表获取到的老师对象
     * */
    Teacher getTeacher(Long account);

    /**
     * 更新数据库中教师的记录
     *
     * @param record 主键代表新的教师对象
     * @return 代表处理数量
     * */
    int updateTeacher(Teacher record);

}