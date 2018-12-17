package com.group12.course.service;

import com.group12.course.dao.KlassDao;
import com.group12.course.entity.Klass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Klass Service 层
 * @author Tan Xue
 * @date 2018/12/12
 */

@Service
public class KlassService {

    @Autowired
    KlassDao klassDao;

    public Klass getKlass(Long id){
        return klassDao.getKlass(id);
    }

    public int deleteKlass(Long id){
        return klassDao.deleteKlass(id);
    }

    public int addKlass(Klass klass){
        return klassDao.addKlass(klass);
    }

    public int updateKlass(Klass klass){
        return klassDao.updateKlass(klass);
    }

    /**
     * 获取某一课程下的所有班级
     * @param courseId 课程id
     * @return 班级列表
     */
    public List<Klass> getAllKlassByCourseId(Long courseId){
        return klassDao.getAllKlassByCourseId(courseId);
    }

}
