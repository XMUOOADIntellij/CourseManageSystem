package com.group12.course.dao;


import com.group12.course.entity.Course;
import com.group12.course.entity.KlassStudent;
import com.group12.course.entity.Student;
import com.group12.course.entity.Team;
import com.group12.course.entity.strategy.ConflictCourseStrategy;
import com.group12.course.entity.strategy.CourseMemberLimitStrategy;
import com.group12.course.mapper.CourseMemberLimitStrategyMapper;
import com.group12.course.mapper.KlassStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseMemberLimitStrategyDao {

    @Autowired
    CourseMemberLimitStrategyMapper courseMemberLimitStrategyMapper;

    @Autowired
    KlassStudentMapper klassStudentMapper;

    public CourseMemberLimitStrategy selectCourseMemberLimitStrategyById(Long id){
        return courseMemberLimitStrategyMapper.selectCourseMemberLimitStrategyById(id);
    }

    public int deleteCourseMemberLimitStrategy(Long id){
        return courseMemberLimitStrategyMapper.deleteCourseMemberLimitStrategy(id);
    }

    public int addCourseMemberLimitStrategy(CourseMemberLimitStrategy record){
        return courseMemberLimitStrategyMapper.addCourseMemberLimitStrategy(record);
    }

    public int updateCourseMemberLimitStrategy(CourseMemberLimitStrategy record){
        return courseMemberLimitStrategyMapper.updateCourseMemberLimitStrategy(record);
    }

    /**
     * 根据传入的 id 和 队伍
     * 判断该队伍是否符合该 id 的策略
     *
     * @param strategyId 策略 id
     * @param team 待判断的队伍
     * @return 是否符合
     */
    public Boolean judgeTeam(Long strategyId, Team team){
        CourseMemberLimitStrategy courseMemberLimitStrategy=courseMemberLimitStrategyMapper.selectCourseMemberLimitStrategyById(strategyId);
        int currentCount =0;
        Course course = courseMemberLimitStrategy.getCourse();
        int min = courseMemberLimitStrategy.getMinMember();
        int max = courseMemberLimitStrategy.getMaxMember();
        Student leader = team.getLeader();
        List<KlassStudent> klassStudents = klassStudentMapper.selectKlassStudentByStudentId(leader.getId());
        for (KlassStudent ks:klassStudents) {
            Course ksCourse = ks.getCourse();
            if (ksCourse.getId().equals(course.getId())){
                currentCount++;
            }
        }
        for (Student member:team.getMembers()) {
            List<KlassStudent> klassStudent = klassStudentMapper.selectKlassStudentByStudentId(member.getId());
            for (KlassStudent ks:klassStudent) {
                Course ksCourse = ks.getCourse();
                if (ksCourse.getId().equals(course.getId())){
                    currentCount++;
                }
            }
        }
        if (currentCount<min){
            return false;
        }
        else if (currentCount>max){
            return false;
        }
        else {
            return true;
        }
    }
}
