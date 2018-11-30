package com.group12.course.service.serviceimpl;

import com.group12.course.dao.ClassDao;
import com.group12.course.entity.Class;
import com.group12.course.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tan Xue
 * 2018年11月30日
 */
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassDao classDao;

    /**
     * 获取所有班级
     * @return List<Class>
     */
    @Override
    public List<Class> listClasses() {
        return classDao.listClasses();
    }

    /**
     * 根据班级id获取班级
     * @param id int
     * @return Class
     */
    @Override
    public Class getClassNameById(int id) {
        return classDao.getClassNameById(id);
    }

    /**
     * 增加班级
     * @param entity Class
     * @return Class
     */
    @Override
    public boolean addClass(Class entity) {
        return classDao.add(entity);
    }

    /**
     * 删除班级
     * @param id int
     * @return Class
     */
    @Override
    public boolean deleteClass(int id) {
        return classDao.remove(id);
    }

    /**
     * 更新班级
     * @param entity Class
     * @return Class
     */
    @Override
    public boolean updateClass(Class entity) {
        return false;
    }
}
