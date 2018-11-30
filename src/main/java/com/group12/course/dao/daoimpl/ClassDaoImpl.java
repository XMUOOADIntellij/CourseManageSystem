package com.group12.course.dao.daoimpl;

import com.group12.course.dao.ClassDao;
import com.group12.course.entity.Class;
import com.group12.course.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tan Xue
 * 2018年11月30日
 */
@Component
public class ClassDaoImpl implements ClassDao {

    @Autowired
    ClassMapper classMapper;

    /**
     * 获得所有班级
     * @return List<Class>
     */
    @Override
    public List<Class> listClasses() {
        return classMapper.getAllClasses();
    }

    /**
     * 根据班级id获得班级
     * @param id int
     * @return Class
     */
    @Override
    public Class getClassNameById(int id) {
        return classMapper.getClassNameById(id);
    }

    /**
     * 增加班级
     * @param entity  Class
     * @return Class
     */
    @Override
    public boolean add(Class entity) {
        return classMapper.add(entity);
    }

    /**
     * 删除班级
     * @param id int
     * @return Class
     */
    @Override
    public boolean remove(int id) {
        return classMapper.remove(id);
    }

    /**
     * 更新班级
     * @param entity  Class
     * @return Class
     */
    @Override
    public boolean update(Class entity) {
        return classMapper.update(entity);
    }
}
