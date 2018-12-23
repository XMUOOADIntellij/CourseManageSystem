package com.group12.course.dao;

import com.group12.course.entity.Klass;
import com.group12.course.entity.KlassStudent;
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
    @Autowired
    KlassStudentDao klassStudentDao;

    public Klass getKlass(Long id){
        return klassMapper.selectKlassById(id);
    }

    /**
     * 删除班级，并且删除班级与班级下所有学生账户的关联
     * @param klassId
     * @return
     */
    public int deleteKlass(Long klassId){
        //删除班级
        int status1 = klassMapper.deleteKlass(klassId);
        //删除学生与班级的关联
        int status2 = klassStudentDao.deleteKlassStudentByKlassId(klassId);
        if(status1 ==0 || status2 == 0){
            return 0;
        }
        else{
            return 1;
        }
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
