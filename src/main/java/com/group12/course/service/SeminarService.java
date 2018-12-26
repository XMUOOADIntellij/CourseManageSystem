package com.group12.course.service;

import com.group12.course.exception.RecordNotFoundException;
import com.group12.course.exception.UnauthorizedOperationException;
import com.group12.course.dao.*;
import com.group12.course.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    ScoreDao scoreDao;
    @Autowired
    KlassStudentDao klassStudentDao;

    /**
     * 判断当前老师 在讨论课共享从课程或主课程中
     * @param teacher 老师
     * @param course 课程
     * @return Boolean
     */
    private Boolean inCourseOrInSubCourse(Teacher teacher,Course course){
        if(courseDao.getCourse(course.getId())==null){
            return  false;
        }
        else {
            for(Course item:courseDao.getSubCourseBySeminarMainCourseId(course.getId())){
                if(course.getTeacher().getId().equals(teacher.getId())){
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 新建讨论课 Service层
     * 新建的是课程讨论课在seminar，需要课程下找到班级在klassSeminar增加记录
     * 从课程不能创建，主课程创建同步到从课程
     * 从课程Seminar无备份，klassSeminar根据从课程班级插入
     * @param record 讨论课记录
     * @return 讨论课Id
     */
    public Long createSeminar(Seminar record, Teacher teacher) {

        Course course = courseDao.getCourse(record.getCourse().getId());
        record.setCourse(courseDao.getCourse(record.getCourse().getId()));

        if (course != null) {
            if (course.getSeminarMainCourse() != null) {
                if (course.getTeacher().getId().equals(
                        teacher.getId())) {
                    //为record增加serial
                    List<Seminar> seminarList = seminarDao.listSeminarByCourseId(course.getId());
                    if (seminarList == null) {
                        record.setSeminarSerial(1);
                    } else {
                        record.setSeminarSerial(seminarList.size() + 1);
                    }
                    return seminarDao.insertSeminar(record);
                } else {
                    throw new UnauthorizedOperationException("不属于自己的课程不能创建讨论课");
                }
            } else {
                throw new UnauthorizedOperationException("从课程不能创建讨论课");
            }
        } else {
            throw new RecordNotFoundException("没有找到课程记录");
        }
    }

    /**
     * 删除讨论课
     * 讨论课只对应主课程，从课程的老师和course对不上所以无法删
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
            Course course = seminar.getCourse();
            if (course.getTeacher().getId().equals(teacher.getId())) {
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

    /**
     * 更改讨论课
     * 讨论课只对应主课程，从课程的老师和course对不上所以无法删
     * @param teacher 老师
     * @param record 记录
     * @return 1成功 0失败
     */
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

    /**
     * 更改班级讨论课
     * @param teacher 老师
     * @param record 记录
     * @return 1成功 0失败
     */
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
            //找到所属课程或主课程
            Course course = klassSeminar.getSeminar().getCourse();
            //老师属于从课程或者主课程
            if (inCourseOrInSubCourse(teacher,course)) {
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
            //找到所属课程或主课程
            Course course = klassSeminar.getSeminar().getCourse();
            //老师属于从课程或者主课程
            if (inCourseOrInSubCourse(teacher,course)) {
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
            //找到所属课程或主课程
            Course course = klassSeminar.getSeminar().getCourse();
            //老师属于从课程或者主课程
            if (inCourseOrInSubCourse(teacher,course)) {
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
            //找到所属课程或主课程
            Course course = klassSeminar.getSeminar().getCourse();
            //老师属于从课程或者主课程
            if (inCourseOrInSubCourse(teacher,course)) {
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

    public KlassSeminar getCurrentSeminar(Teacher teacher) {
        List<Course> courseList = courseDao.getCourseByTeacherId(teacher.getId());
        if (courseList == null) {
            return null;
        } else {
            //所有课程
            for (Course course : courseList) {
                //所有讨论课
                for (Seminar seminar : seminarDao.listSeminarByCourseId(course.getId())) {
                    //所有班级讨论课
                    for (KlassSeminar klassSeminar : klassSeminarDao.listKlassSeminarBySeminarId(seminar.getId())){
                        //讨论课所处状态，未开始0，正在进行1，已结束2，暂停3
                        if(klassSeminar.getSeminarStatus() == 1){
                            return klassSeminar;
                        }
                    }
                }
            }
            return  null;
        }
    }

    public KlassSeminar getCurrentSeminar(Student student){
        List<KlassStudent> klassStudents =klassStudentDao.selectKlassStudentByStudentId(student.getId());
        if(klassStudents!=null){
            List<Long> klassIdList = new ArrayList<>();
            for(KlassStudent klassStudent : klassStudents){
                klassIdList.add(klassStudent.getKlass().getId());
            }

            for(KlassSeminar klassSeminar: klassSeminarDao.listKlassSeminarByKlassIdList(klassIdList)){
                if(klassSeminar.getSeminarStatus()==1){
                    return  klassSeminar;
                }
            }
            return null;
        }else{
            return null;
        }
    }

}
