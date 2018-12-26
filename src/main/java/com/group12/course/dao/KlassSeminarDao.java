package com.group12.course.dao;

import com.group12.course.entity.Course;
import com.group12.course.entity.KlassSeminar;
import com.group12.course.entity.Teacher;
import com.group12.course.mapper.KlassSeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 班级讨论课相关 Dao层
 *
 * @author Y Jiang
 * @date 2018/12/12
 */
@Component
public class KlassSeminarDao {
    @Autowired
    KlassSeminarMapper klassSeminarMapper;
    @Autowired
    CourseDao courseDao;

    public KlassSeminar selectKlassSeminarBySeminarIdAndClassId(Long seminarId, Long classId) {
        return klassSeminarMapper.selectKlassSeminarBySeminarIdAndKlassId(seminarId, classId);
    }

    public KlassSeminar selectKlassSeminarById(Long id) {
        return klassSeminarMapper.selectKlassSeminarById(id);
    }

    public List<KlassSeminar> listKlassSeminarBySeminarId(Long seminarId) {
        return klassSeminarMapper.listKlassSeminarBySeminarId(seminarId);
    }

    public Integer insertKlassSeminarList(List<KlassSeminar> record) {
        return klassSeminarMapper.insertKlassSeminarList(record);
    }

    public Integer deleteKlassSeminarBySeminarId(Long seminarId) {
        return klassSeminarMapper.deleteBySeminarId(seminarId);
    }

    public Integer updateKlassSeminar(KlassSeminar klassSeminar) {
        if (klassSeminar.getId() != null) {
            return klassSeminarMapper.updateKlassSeminar(klassSeminar);
        } else {
            return null;
        }
    }

    public List<KlassSeminar> listKlassSeminarBySeminarIdList(List<Long> seminarId) {
        return klassSeminarMapper.listKlassSeminarBySeminarIdList(seminarId);
    }

    public List<KlassSeminar> listKlassSeminarByKlassIdList(List<Long> klassIdList) {
        return klassSeminarMapper.listKlassSeminarByKlassIdList(klassIdList);
    }

    public Boolean checkTeacherHasKlassSeminar(Teacher teacher, Long klassSeminarId) {
        KlassSeminar klassSeminar = selectKlassSeminarById(klassSeminarId);

        if (klassSeminar != null) {
           Course course = klassSeminar.getSeminar().getCourse();
            if(course.getTeacher().getId().equals(teacher.getId())){
                return  true;
            }else{
                //是从课程，被分享讨论课的情况
                for(Course item:courseDao.getSubCourseBySeminarMainCourseId(course.getId())){
                    if(item.getTeacher().getId().equals(teacher.getId())){
                        return true;
                    }
                }
                return false;
            }
        } else {
            return false;
        }

    }
}
