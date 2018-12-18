package com.group12.course.service;

import com.group12.course.dao.*;
import com.group12.course.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 讨论课相关Service
 * @author Y Jiang
 * @date  2018/12/14
 */
@Service
public class SeminarService {
    @Autowired
    SeminarDao seminarDao;
    @Autowired
    KlassSeminarDao klassSeminarDao;
    @Autowired
    CourseDao courseDao;

    /**
     * 新建讨论课 Service层
     * 新建的是课程讨论课在seminar，需要课程下找到班级在klassSeminar增加记录
     * @param record 讨论课记录
     * @return 讨论课Id
     */
    public Long createSeminar(Seminar record,Teacher teacher){
        record.setCourse(courseDao.getCourse(record.getCourse().getId()));
        if(record.getCourse().getTeacher().getId().equals(
                teacher.getId())){
        return  seminarDao.createSeminar(record);
        }
        else{
            return null;
            //TODO 权限,不能在非自己课程
        }
    }


    /**
     * 删除讨论课
     * @param seminarId 讨论课id
     * @return 1成功 0失败
     */
    public Integer deleteSeminar(Long seminarId,Teacher teacher){
        //TODO 验证老师教的课中有这个seminar
        if(seminarDao.selectSeminarById(seminarId).getCourse().getTeacher().getId().equals(
                teacher.getId())){
            return seminarDao.deleteSeminarById(seminarId);}
      else{
          //TODO 权限
          return null;
        }
    }


    /**
     * 寻找班级讨论课
     * @param seminarId 讨论课id
     * @param classId 班级id
     * @return
     */
    public KlassSeminar selectKlassSeminarBySeminarIdAndClassId(Long seminarId,Long classId){
        return klassSeminarDao.getKlassSeminarBySeminarIdAndClassId(seminarId,classId);
    }

    public Integer updateSeminar(Seminar seminar){
       return seminarDao.updateSeminar(seminar);
    }

    public Integer updateKlassSeminar(Long seminarId,Long classId,KlassSeminar record){
        KlassSeminar klassSeminar = selectKlassSeminarBySeminarIdAndClassId(seminarId,classId);
        record.setId(klassSeminar.getId());
        return  klassSeminarDao.updateKlassSeminar(record);
    }
}
