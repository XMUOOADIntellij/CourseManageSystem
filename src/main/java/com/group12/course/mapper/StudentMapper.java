package com.group12.course.mapper;

import com.group12.course.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

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
     * @param id 代表用户账号id
     * @return 代表处理数量
     * */
    int deleteStudentByID(Long id);

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
     * @param account 代表用户账号
     * @return 代表获取到的学生对象
     * */
    Student getStudentByAccount(String account);

    /**
     * 更新数据库中学生的记录
     *
     * @param record 代表新的学生对象
     * @return 代表处理数量
     * */
    int updateStudent(Student record);

    /**
     * 更新数据库中学生的记录
     *
     * @param record 代表新的学生对象
     * @return 代表处理数量
     * */
    int updateStudentByID(Student record);

    /**
     * 获取所有学生的记录
     *
     * @return 返回所有的学生的列表
     * */
    List<Student> getAllStudent();

    /**
     * 通过姓名来获取学生记录
     * @param name 学生姓名
     * @return 所有同名的学生
     * */
    List<Student> getStudentByName(String name);
}