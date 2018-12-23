package com.group12.course.dao;

import com.group12.course.entity.Klass;
import com.group12.course.entity.KlassSeminar;
import com.group12.course.entity.Seminar;
import com.group12.course.entity.Teacher;
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
    ScoreDao scoreDao;
    @Autowired
    RoundDao roundDao;

    /**
     * 创建课程讨论课
     * @param record 讨论课记录
     * @return  id
     */
    public Long insertSeminar(Seminar record) {
        List<Klass> classRecord;
        List<KlassSeminar> klassSeminarsRecord = new ArrayList<>();
        // 判断当前课程存在
        if (record.getCourse().getId() != null) {
            if (record.getMaxTeam() != null ||
                    record.getSeminarName() != null ||
                    record.getVisible() != null ||
                    record.getSeminarSerial() != null) {
                //Seminar表插入记录
                seminarMapper.insertSeminar(record);
                //寻找该课程下的班级
                classRecord = klassDao.getAllKlassByCourseId(record.getCourse().getId());
                //生成班级讨论课的记录
                for (Klass klass : classRecord) {
                    KlassSeminar tempKlassSeminar = new KlassSeminar();
                    tempKlassSeminar.setKlass(klass);
                    tempKlassSeminar.setSeminar(record);
                    tempKlassSeminar.setSeminarStatus(0);
                    tempKlassSeminar.setReportDdl(null);
                    klassSeminarsRecord.add(tempKlassSeminar);
                }
                //插入班级讨论课记录
                klassSeminarDao.insertKlassSeminarList(klassSeminarsRecord);
                if (record.getRound() == null) {
                    //TODO 新建轮
                }
                return record.getId();
            } else {
                //TODO 讨论课信息不完全
                return null;
            }
        } else {
            //TODO CourseNotFound
            return null;
        }
    }


    /**
     * 通过id获得课程讨论课
     * @param id id
     * @return Seminar
     */
    public Seminar selectSeminarById(Long id){
        return seminarMapper.selectSeminarById(id);
    }

    /**
     * 更新讨论课信息
     * @param seminar 记录
     * @return 1成功 0失败
     */
    public Integer updateSeminar(Seminar seminar){
        return seminarMapper.updateSeminar(seminar);
    }

    /**
     * 根据id删除课程讨论课
     * @param seminarId 讨论课id
     * @return 1成功 0失败
     */
    public Integer deleteSeminarById(Long seminarId){
        if(seminarMapper.selectSeminarById(seminarId)!=null) {
            List<KlassSeminar> klassSeminarList;
            klassSeminarList = klassSeminarDao.selectKlassSeminarBySeminarId(seminarId);

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
                scoreDao.deleteSeminarScoreByKlassSeminarId(klassSeminarId);
            }
            //然后删除class_seminar
            klassSeminarDao.deleteKlassSeminarBySeminarId(seminarId);

            //删除讨论课
            return seminarMapper.deleteSeminarById(seminarId);
        }
        return null;
    }


    public List<Seminar> listSeminarByRoundId(Long roundId){
        return seminarMapper.listSeminarByRoundId(roundId);
    }
}
