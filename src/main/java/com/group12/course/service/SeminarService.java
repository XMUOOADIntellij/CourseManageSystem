package com.group12.course.service;

import com.group12.course.exception.RecordNotFoundException;
import com.group12.course.exception.UnauthorizedOperationException;
import com.group12.course.dao.*;
import com.group12.course.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ConcurrentModificationException;

/**
 * 讨论课相关Service
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
    @Autowired
    ScoreDao scoreDao;

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
            throw new UnauthorizedOperationException("can not create seminar under a course belongs others");
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
                throw new UnauthorizedOperationException("只有当前课的老师可删除此讨论课");
            }
        } else {
            throw new RecordNotFoundException("找不到讨论课");
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

    public Seminar selectSeminarById(Long seminarId) {
        return seminarDao.selectSeminarById(seminarId);
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
                throw new UnauthorizedOperationException("只有当前课的老师可更新此讨论课");
            }
        } else {
            throw new RecordNotFoundException("未找到该讨论课记录");
        }
    }

    public Integer updateKlassSeminar(KlassSeminar record, Teacher teacher) {
        KlassSeminar klassSeminar;
        try {
            klassSeminar = klassSeminarDao.
                    selectKlassSeminarBySeminarIdAndClassId(record.getSeminar().getId(), record.getKlass().getId());
        } catch (ConcurrentModificationException e) {
            klassSeminar = klassSeminarDao.
                    selectKlassSeminarBySeminarIdAndClassId(record.getSeminar().getId(), record.getKlass().getId());
        }

        if (klassSeminar != null) {
            if (klassSeminar.getSeminar().getCourse().getTeacher().getId()
                    .equals(teacher.getId())) {
                record.setId(klassSeminar.getId());
                return klassSeminarDao.updateKlassSeminar(record);
            } else {
                throw new UnauthorizedOperationException("只有当前课的老师可更新此班级讨论课");
            }
        } else {
            throw new RecordNotFoundException("找不到班级讨论课");
        }
    }

    public KlassSeminar pauseSeminar(Teacher teacher, Long seminarId, Long classId) {
        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, classId);
        if (klassSeminar != null) {
            if (teacher.getId().equals(klassSeminar.getSeminar().getCourse().getTeacher().getId())) {
                //讨论课所处状态，未开始0，正在进行1，已结束2，暂停3
                klassSeminar.setSeminarStatus(3);
                if (klassSeminarDao.updateKlassSeminar(klassSeminar) == 1) {
                    return klassSeminar;
                } else {
                    return null;
                }
            } else {
                throw new UnauthorizedOperationException("只有当前课的老师可暂停该讨论课");
            }
        } else {
            throw new RecordNotFoundException("找不到班级讨论课");
        }
    }

    public KlassSeminar startSeminar(Teacher teacher, Long seminarId, Long classId) {
        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, classId);
        if (klassSeminar != null) {
            if (teacher.getId().equals(klassSeminar.getSeminar().getCourse().getTeacher().getId())) {
                //讨论课所处状态，未开始0，正在进行1，已结束2，暂停3
                klassSeminar.setSeminarStatus(1);
                if (klassSeminarDao.updateKlassSeminar(klassSeminar) == 1) {
                    //开始成功后，为该班级小组添加成绩记录
                    scoreDao.initialScoreBeforeKlassSeminar(klassSeminar.getId());
                    return klassSeminar;
                } else {
                    return null;
                }
            } else {
                throw new UnauthorizedOperationException("only teacher in this course can operate");
            }
        } else {
            throw new RecordNotFoundException("找不到班级讨论课");
        }
    }

    public KlassSeminar endSeminar(Teacher teacher, Long seminarId, Long classId) {
        KlassSeminar klassSeminar = klassSeminarDao.selectKlassSeminarBySeminarIdAndClassId(seminarId, classId);
        if (klassSeminar != null) {
            if (teacher.getId().equals(klassSeminar.getSeminar().getCourse().getTeacher().getId())) {
                //讨论课所处状态，未开始0，正在进行1，已结束2，暂停3
                klassSeminar.setSeminarStatus(2);
                if (klassSeminarDao.updateKlassSeminar(klassSeminar) == 1) {
                    return klassSeminar;
                } else {
                    return null;
                }
            } else {
                throw new UnauthorizedOperationException("只有当前课的老师可结束此讨论课");
            }
        } else {
            throw new RecordNotFoundException("找不到班级讨论课");
        }
    }
}
