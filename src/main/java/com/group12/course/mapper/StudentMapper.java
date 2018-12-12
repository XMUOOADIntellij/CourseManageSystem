package com.group12.course.mapper;

import com.group12.course.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Student Mapper 层接口
 * @author Xu Gang
 * @date 2018年12月10日
 */
@Mapper
@Component
public interface StudentMapper {

    /**
     * 删除数据库中学生的记录
     *
     * @param account 主键代表用户账号
     * @return 代表处理数量
     * */
    int deleteStudentByAccount(String account);

    /**
     * 在数据库中添加学生的记录
     *
     * @param record 待添加的学生
     * @return 代表处理数量
     * */
    int addStudent(Student record);

    /**
     * 获取数据库中学生的记录
     *
     * @param account 主键代表用户账号
     * @return 代表获取到的学生对象
     * */
    Student getStudentByAccount(String account);

    /**
     * 更新数据库中学生的记录
     *
     * @param record 主键代表新的学生对象
     * @return 代表处理数量
     * */
    int updateStudent(Student record);

}