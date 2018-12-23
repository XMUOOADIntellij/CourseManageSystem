package com.group12.course.service;

import com.group12.course.dao.*;
import com.group12.course.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * 讨论课相关Service
 *
 * @author Y Jiang
 * @date 2018/12/14
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
     *
     * @param record 讨论课记录
     * @return 讨论课Id
     */
    public Long createSeminar(Seminar record, Teacher teacher) {
        record.setCourse(courseDao.getCourse(record.getCourse().getId()));
        if (record.getCourse().getTeacher().getId().equals(
                teacher.getId())) {
            return seminarDao.insertSeminar(record);
        } else {
            System.out.println(record.getCourse().getTeacher().getId());
            System.out.println(teacher.getId());
            return null;
            //TODO 权限,不能在非自己课程
        }
    }

    /**
     * 删除讨论课
     *
     * @param seminarId 讨论课id
     * @return 1成功 0失败
     */
    public Integer deleteSeminar(Long seminarId, Teacher teacher) {
        Seminar seminar;
        try {
            seminar = seminarDao.selectSeminarById(seminarId);
        } catch (ConcurrentModificationException e) {
            seminar = seminarDao.selectSeminarById(seminarId);
        }
        if (seminar != null) {
            //验证老师教的课中有这个seminar
            if (seminar.getCourse().getTeacher().getId().equals(
                    teacher.getId())) {
                return seminarDao.deleteSeminarById(seminarId);
            } else {
                //TODO 权限
                System.out.println("quanxian");
                return null;
            }
        } else {
            System.out.println("notfound");
            return null;
            //TODO SeminarNotFound
        }
    }

    /**
     * 寻找班级讨论课
     *
     * @param seminarId 讨论课id
     * @param classId   班级id
     * @return
     */
    public KlassSeminar selectKlassSeminar(Long seminarId, Long classId) {
        return klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, classId);
    }

    public Integer updateSeminar(Seminar record, Teacher teacher) {
        Seminar seminar;
        try {
            seminar = seminarDao.selectSeminarById(record.getId());
        } catch (ConcurrentModificationException e) {
            seminar = seminarDao.selectSeminarById(record.getId());
        }
        if (seminar != null) {
            if (seminar.getCourse().getTeacher().getId().equals(teacher.getId())) {
                return seminarDao.updateSeminar(record);
            } else {
                //TODO 权限
                return null;
            }
        } else {
            //SeminarNotFound
            return null;
        }
    }

    public Integer updateKlassSeminar(KlassSeminar record,Teacher teacher) {
        KlassSeminar klassSeminar;
        try {
            klassSeminar =klassSeminarDao.
                    selectKlassSeminarBySeminarIdAndClassId(record.getSeminar().getId(), record.getKlass().getId());;
        } catch (ConcurrentModificationException e) {
            klassSeminar =klassSeminarDao.
                    selectKlassSeminarBySeminarIdAndClassId(record.getSeminar().getId(), record.getKlass().getId());;
        }

        if(klassSeminar!=null){
            if(klassSeminar.getSeminar().getCourse().getTeacher().getId()
                    .equals(teacher.getId())){
                record.setId(klassSeminar.getId());
                return klassSeminarDao.updateKlassSeminar(record);
            }
            else{
                //TODO 权限
                return null;
            }
        }
        else{
            return null;
            //TODO SeminarNotFound
        }
    }

    public KlassSeminar pauseSeminar(Teacher teacher){
        return null;
    }
}
