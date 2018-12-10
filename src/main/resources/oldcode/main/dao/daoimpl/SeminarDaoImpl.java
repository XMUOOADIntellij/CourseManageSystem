package com.group12.course.dao.daoimpl;

import com.group12.course.dao.SeminarDao;
import com.group12.course.entity.Seminar;
import com.group12.course.mapper.SeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Seminar Dao 实现
 * @author Y Jiang
 * @date 2018/12/1
 */
@Component
public class SeminarDaoImpl implements SeminarDao {
    @Autowired
    private SeminarMapper seminarMapper;

    @Override
    public  int deleteByPrimaryKey(Long id){
        return seminarMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Seminar record){
     return seminarMapper.insert(record);
    }

    @Override
    public int insertSelective(Seminar record){
        return seminarMapper.insertSelective(record);
    }

    @Override
    public Seminar selectByPrimaryKey(Long id){
        return seminarMapper.selectByPrimaryKey(id);
    }
    @Override
    public int updateByPrimaryKeySelective(Seminar record){
        return seminarMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Seminar record){
        return seminarMapper.updateByPrimaryKeySelective(record);
    }
}
