package com.group12.course.service.serviceimpl;

import com.group12.course.dao.ClassDao;
import com.group12.course.entity.Class;
import com.group12.course.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Class service层接口实现
 * @author Tan Xue
 * @date 2018/11/30
 */
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassDao classDao;

    /**
     * 找到当前课程的所有班级
     * @param courseId String
     * @return List  班级列表
     */
    public List<Class> getAllClasses(Long courseId){
        return classDao.listClasses(courseId);
    }

    /**
     * 通过班级id获取班级
     * @param id 班级号
     * @return Class对象
     */
    public Class getClassById(String id){
        return classDao.selectByPrimaryKey(id);
    }

    /**
     * 添加班级
     * @param record 班级
     * @return 添加的班级数
     */
    public int addClass(Class record){
        return classDao.insert(record);
    }

    /**
     * 修改班级
     * @param record 班级
     * @return 修改个数
     */
    public int updateClass(Class record){
        return classDao.updateByPrimaryKey(record);
    }

    /**
     * 通过班级id删除班级
     * @param id 班级号
     * @return 删除个数
     */
    public int deleteClass(String id){
        return classDao.deleteByPrimaryKey(id);
    }
}
