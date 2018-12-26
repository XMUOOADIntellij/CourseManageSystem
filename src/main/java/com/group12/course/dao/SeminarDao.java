package com.group12.course.dao;

import com.group12.course.entity.Course;
import com.group12.course.exception.InformationException;
import com.group12.course.entity.Klass;
import com.group12.course.entity.KlassSeminar;
import com.group12.course.entity.Seminar;
import com.group12.course.mapper.SeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * 与讨论课相关Dao层
 * @author Y Jiang
 * @date 2018/12/22
 */
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
     *
     * @param record 讨论课记录
     * @return id
     */
    public Long insertSeminar(Seminar record) {

        List<Klass> classRecord;
        List<Course> courseList;
        List<Long> courseIdList = new ArrayList<>();
        List<KlassSeminar> klassSeminarsRecord = new ArrayList<>();
        Course course = courseDao.getCourse(record.getCourse().getId());

        // 判断当前课程存在
        if (course != null) {

            //如果是需要新建轮，则新建轮 插入->获取->设置
            if (record.getRound() == null) {
                record.setRound(
                        roundDao.getRound(
                                roundDao.addRound(course.getId())));
            }

            //查找所有从课程
            courseList = courseDao.getSubCourseBySeminarMainCourseId(course.getId());
            courseList.add(course);
            for(Course item:courseList){
                courseIdList.add(item.getId());
            }

            //Seminar表插入记录
            seminarMapper.insertSeminar(record);

            //寻找主课程以及从课程下的班级
            classRecord = klassDao.getAllKlassByCourseIdList(courseIdList);

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
            return record.getId();
        } else {
            throw new InformationException("讨论课不属于任何课程");
        }
    }

    /**
     * 根据id删除课程讨论课
     *
     * @param seminarId 讨论课id
     * @return 1成功 0失败
     */
    public Integer deleteSeminarById(Long seminarId) {
        if (seminarMapper.selectSeminarById(seminarId) != null) {
            klassSeminarDao.deleteKlassSeminarBySeminarId(seminarId);
            //删除讨论课
            return seminarMapper.deleteSeminarById(seminarId);
        }
        return null;
    }

    /**
     * 通过id获得课程讨论课
     *
     * @param id id
     * @return Seminar
     */
    public Seminar selectSeminarById(Long id) {
        return seminarMapper.selectSeminarById(id);
    }

    /**
     * 更新讨论课信息
     *
     * @param seminar 记录
     * @return 1成功 0失败
     */
    public Integer updateSeminar(Seminar seminar) {
        return seminarMapper.updateSeminar(seminar);
    }

    public List<Seminar> listSeminarByRoundId(Long roundId) {
        return seminarMapper.listSeminarByRoundId(roundId);
    }

    public List<Seminar> listSeminarByCourseId(Long courseId) {
        return seminarMapper.listSeminarByCourseId(courseId);
    }

}
