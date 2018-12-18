package com.group12.course.dao;

import com.group12.course.entity.Klass;
import com.group12.course.entity.KlassSeminar;
import com.group12.course.entity.Seminar;
import com.group12.course.mapper.SeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeminarDao {

    @Autowired
    SeminarMapper seminarMapper;
    @Autowired
    CourseDao courseDao;
    @Autowired
    KlassDao klassDao;
    @Autowired
    KlassSeminarDao klassSeminarDao;
    @Autowired
    QuestionDao questionDao;
    @Autowired
    AttendanceDao attendanceDao;
    @Autowired
    SeminarScoreDao seminarScoreDao;

    /**
     * 创建课程讨论课
     * @param record 讨论课记录
     * @return  id
     */
    public Long createSeminar(Seminar record){
        List<Klass> classRecord;
        List<KlassSeminar> klassSeminarsRecord = new ArrayList<>();
        // 判断当前课程存在
        if(courseDao.getCourse(record.getCourse().getId())!=null){
            //Seminar表插入记录
            seminarMapper.insertSeminar(record);
            //寻找该课程下的班级
            classRecord = klassDao.getAllKlassByCourseId(record.getCourse().getId());
            //生成班级讨论课的记录
            for(Klass klass : classRecord){
                KlassSeminar tempKlassSeminar = new KlassSeminar();
                tempKlassSeminar.setKlass(klass);
                tempKlassSeminar.setSeminar(record);
                tempKlassSeminar.setSeminarStatus(0);
                tempKlassSeminar.setReportDdl(null);
                klassSeminarsRecord.add(tempKlassSeminar);
            }
            //插入班级讨论课记录
            klassSeminarDao.insertByList(klassSeminarsRecord);
            if(record.getRound()==null){
                //TODO 若没有轮，默认新建
            }
            return record.getId();
        }
        else{
            return null;
        }
    }


    public Seminar selectSeminarById(Long id){
        return seminarMapper.selectSeminarById(id);
    }

    public Integer updateSeminar(Seminar seminar,Long seminarId){
        return seminarMapper.updateSeminar(seminar,seminarId);
    }

    /**
     * 根据id删除课程讨论课
     * @param seminarId 讨论课id
     * @return 1成功 0失败
     */
    public Integer deleteSeminarById(Long seminarId){
        if(seminarMapper.selectSeminarById(seminarId)!=null) {
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
            return seminarMapper.deleteSeminarById(seminarId);
        }
        return null;
    }
}
