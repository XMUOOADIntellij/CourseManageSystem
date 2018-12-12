package com.group12.course.dao;

import com.group12.course.entity.Student;
import com.group12.course.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * Student dao 层对应接口的实现
 * @author Xu Gang
 * @date 2018年12月10日
 */
@Component
public class StudentDao {

    @Autowired
    StudentMapper studentMapper;

    private static Pattern NUMBER_PATTERN = Pattern.compile("[0-9]+");
    final String defaultPassword = "123456";

    /**
     * 学生登陆
     *
     * @param student 传入的学生对象
     * @return 若密码符合，返回真实的学生对象
     * 否则返回一个新对象
     * */
    public Student login(Student student){
        if (student.getPassword()==null||student.getAccount()==null){
            return new Student();
        }
        else{
            Student tempStudent=studentMapper.getStudentByAccount(student.getAccount());
            if(tempStudent.getPassword().equals(student.getPassword())){
                return tempStudent;
            }
            else {
                return new Student();
            }
        }
    }

    /**
     * 获取个人信息
     *
     * @param account 用户账户
     * @return 账户存在则返回该对象，否则则返回一个新对象
     * */
    public Student getStudent(String account){
        if (account==null){
            return new Student();
        }
        else {
            return studentMapper.getStudentByAccount(account);
        }
    }

    /**
     * 获取所有学生的记录
     *
     * @return 返回所有的学生的列表
     * */
    public List<Student> getAllStudent(){
        return studentMapper.getAllStudent();
    }

    /**
     * 通过账号或者姓名获取学生信息
     * @param param 可以是学生姓名或者账号
     * @return 返回符合的学生的列表
     * 当是通过账号查询时，列表只会包含一个
     * */
    public List<Student> getStudentByParam(String param){
        if (NUMBER_PATTERN.matcher(param).matches()){
            List<Student> list = new ArrayList<>();
            list.add(getStudent(param));
            return list;
        }
        else {
            return studentMapper.getStudentByName(param.trim());
        }
    }

    /**
     * 删除数据库中学生的记录
     *
     * @param id 代表用户id
     * @return 代表处理数量
     * */
    public int deleteStudent(Long id){
        return studentMapper.deleteStudentByID(id);
    }

    public int addStudent(Student student){
        return studentMapper.addStudent(student);
    }

    /**
     * 根据ID，更新数据库中学生的记录
     *
     * @param student 代表新的学生对象
     * @return 代表处理数量
     * */
    public int changeStudentByID(Student student){
        return studentMapper.updateStudentByID(student);
    }

    /**
     * 重置学生密码
     *
     * @param id 主键id
     * @return 代表处理数量
     * */
    public int resetPassword(Long id){
        return changeStudentByID(new Student(id,defaultPassword));
    }

    /**
     * 根据账号，更新数据库中学生的记录
     *
     * @param student 代表新的学生对象
     * @return 代表处理数量
     * */
    public int changeStudent(Student student){
        return studentMapper.updateStudent(student);
    }
}
