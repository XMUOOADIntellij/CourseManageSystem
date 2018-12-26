package com.group12.course.dao;

import com.group12.course.entity.Course;
import com.group12.course.entity.KlassSeminar;
import com.group12.course.entity.Question;
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
    @Autowired
    QuestionDao questionDao;
    @Autowired
    AttendanceDao attendanceDao;
    @Autowired
    ScoreDao scoreDao;

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
        /**
         *  根据找到的classseminar
         *  删除attendance
         *  删除question
         *  删除seminar_score
         */
        for (KlassSeminar klassSeminar : listKlassSeminarBySeminarId(seminarId)) {
            Long klassSeminarId = klassSeminar.getId();
            questionDao.deleteQuestionByKlassSeminarId(klassSeminarId);
            attendanceDao.deleteAttendanceByKlassSeminarId(klassSeminarId);
            scoreDao.deleteSeminarScoreByKlassSeminarId(klassSeminarId);
        }
        return klassSeminarMapper.deleteBySeminarId(seminarId);
    }

    public Integer deleteKlassSeminarByKlassId(Long klassId) {

        /**
         *  根据找到的classseminar
         *  删除attendance
         *  删除question
         *  删除seminar_score
         */
        for (KlassSeminar klassSeminar : listKlassSeminarByKlassId(klassId)) {
            Long klassSeminarId = klassSeminar.getId();
            questionDao.deleteQuestionByKlassSeminarId(klassSeminarId);
            attendanceDao.deleteAttendanceByKlassSeminarId(klassSeminarId);
            scoreDao.deleteSeminarScoreByKlassSeminarId(klassSeminarId);
        }
        return klassSeminarMapper.deleteByKlassId(klassId);
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

    public List<KlassSeminar> listKlassSeminarByKlassId(Long klassId) {
        return klassSeminarMapper.listKlassSeminarByKlassId(klassId);
    }

}
