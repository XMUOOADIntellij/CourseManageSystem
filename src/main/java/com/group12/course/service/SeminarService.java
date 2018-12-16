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
    CourseDao courseDao;
    @Autowired
    KlassDao klassDao;
    @Autowired
    KlassSeminarDao klassSeminarDao;
    @Autowired
    RoundDao roundDao;
    @Autowired
    AttendanceDao attendanceDao;
    @Autowired
    QuestionDao questionDao;
    @Autowired
    SeminarScoreDao seminarScoreDao;

    /**
     * 新建讨论课 Service层
     * 新建的是课程讨论课在seminar，需要课程下找到班级在klassSeminar增加记录
     * @param record 讨论课记录
     * @return 讨论课Id
     */
    public Long createSeminar(Seminar record){
        List<Klass> classRecord;
        List<KlassSeminar> klassSeminarsRecord = new ArrayList<>();
        // 判断当前课程存在
        if(courseDao.getCourse(record.getCourse().getId())!=null){
            //Seminar表插入记录
            seminarDao.createSeminar(record);
            //寻找该课程下的班级
            classRecord = klassDao.getAllKlassByCourseId(record.getCourse().getId());
            //生成班级讨论课的记录
            for(Klass klass : classRecord){
                KlassSeminar tempKlassSeminar = new KlassSeminar();
                tempKlassSeminar.setKlass(klass);
                tempKlassSeminar.setSeminar(record);
                tempKlassSeminar.setReportDdl(null);
                tempKlassSeminar.setSeminarStatus(0);
                klassSeminarsRecord.add(tempKlassSeminar);
            }
            //插入班级讨论课记录
            klassSeminarDao.insertByList(klassSeminarsRecord);
            if(record.getRound()==null){
                //TODO 若没有轮，默认新建
            }
            return null;

        }
        else{
            return null;
        }
    }


    /**
     * 删除讨论课
     * @param seminarId 讨论课id
     * @return 1成功 0失败
     */
    public Integer deleteSeminar(Long seminarId){
        //TODO 返回值
        if(seminarDao.selectSeminarById(seminarId)!=null) {
            List<KlassSeminar> klassSeminarList;
            klassSeminarList = klassSeminarDao.getKlassSeminarBySeminarId(seminarId);

            /**
             *  根据找到的classseminar
             *  删除attendance
             *  删除question
             *  删除seminar_score
             */
            for (KlassSeminar item : klassSeminarList) {
                Long klassSeminarId = item.getId();
                questionDao.deleteQuestionByKlassSeminarId(klassSeminarId);
                attendanceDao.deleteAttendanceByKlassSeminarId(klassSeminarId);
                seminarScoreDao.deleteSeminarScoreByKlassSeminarId(klassSeminarId);
            }
            //然后删除class_seminar
            klassSeminarDao.deleteKlassSeminarBySeminarId(seminarId);
            //删除讨论课
            return seminarDao.deleteSeminarById(seminarId);
        }
        return null;
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

    public Integer updateSeminar(Seminar seminar,Long seminarId){
       return seminarDao.updateSeminar(seminar,seminarId);
    }

    public Integer updateKlassSeminar(Long seminarId,Long classId,KlassSeminar klassSeminar){
        return  klassSeminarDao.updateKlassSeminar(seminarId,classId,klassSeminar);
    }
}
