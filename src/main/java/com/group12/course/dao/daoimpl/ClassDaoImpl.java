package com.group12.course.dao.daoimpl;

import com.group12.course.dao.ClassDao;
import com.group12.course.entity.Class;
import com.group12.course.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class Dao层接口实现
 * @author Tan Xue
 * @date 2018/11/30
 */

@Component
public class ClassDaoImpl implements ClassDao {

    @Autowired
    ClassMapper classMapper;

    @Override
    public List<Class> listClasses(Long courseId){return classMapper.listClasses(courseId);}

    @Override
    public int deleteByPrimaryKey(String id) {
        return classMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Class record) {
        return classMapper.insert(record);
    }

    @Override
    public int insertSelective(Class record) {
        return classMapper.insertSelective(record);
    }

    @Override
    public Class selectByPrimaryKey(String id) {
        return classMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Class record) {
        return classMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Class record) {
        return classMapper.updateByPrimaryKey(record);
    }
}
