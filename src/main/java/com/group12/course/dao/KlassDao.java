package com.group12.course.dao;

import com.group12.course.entity.Klass;
import com.group12.course.mapper.KlassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Klass Dao 层
 * @author Tan Xue
 * @date 2018/12/12
 */

@Component
public class KlassDao {
    @Autowired
    KlassMapper klassMapper;

    public Klass getKlass(Long id){
        return klassMapper.selectKlassById(id);
    }

    public int deleteKlass(Long id){
        return klassMapper.deleteKlass(id);
    }

    public int addKlass(Klass klass){
        return klassMapper.addKlass(klass);
    }

    public int updateKlass(Klass klass){
        return klassMapper.updateKlass(klass);
    }

    /**
     * 获取某一课程下的所有班级
     * @param courseId 课程id
     * @return 班级列表
     */
    public List<Klass> getAllKlassByCourseId(Long courseId){
        return klassMapper.getAllKlassByCourseId(courseId);
    }
}
