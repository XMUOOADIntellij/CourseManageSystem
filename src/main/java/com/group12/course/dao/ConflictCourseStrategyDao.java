package com.group12.course.dao;

import com.group12.course.entity.*;
import com.group12.course.entity.strategy.ConflictCourseStrategy;
import com.group12.course.entity.strategy.MemberLimitStrategy;
import com.group12.course.mapper.ConflictCourseStrategyMapper;
import com.group12.course.mapper.KlassStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConflictCourseStrategyDao {

    @Autowired
    ConflictCourseStrategyMapper conflictCourseStrategyMapper;

    @Autowired
    KlassStudentMapper klassStudentMapper;

    public List<ConflictCourseStrategy> selectConflictCourseStrategyById(Long id){
        return conflictCourseStrategyMapper.selectConflictCourseStrategyById(id);
    }


    public int addConflictCourseStrategy(ConflictCourseStrategy record){
        return conflictCourseStrategyMapper.addConflictCourseStrategy(record);
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
        List<ConflictCourseStrategy> conflictCourseStrategy = conflictCourseStrategyMapper.selectConflictCourseStrategyById(strategyId);
        if (conflictCourseStrategy==null||conflictCourseStrategy.isEmpty()){
            return true;
        }
        for (ConflictCourseStrategy strategy:conflictCourseStrategy) {
            Course course = strategy.getCourse();
            KlassStudent klassStudent = klassStudentMapper.selectKlassStudentByCourseIdAndStudentId(course.getId(),
                    team.getLeader().getId());
            if (klassStudent!=null){
                return false;
            }
            else {
                for (Student member:team.getMembers()) {
                    KlassStudent ks = klassStudentMapper.selectKlassStudentByCourseIdAndStudentId(course.getId(),
                            member.getId());
                    if (ks!=null){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 计算下一条要插入的记录的id
     * @return
     */
    public Long calculateId(){
        Long maxId = new Long(0) ;
        List<ConflictCourseStrategy> conflictCourseStrategyList = conflictCourseStrategyMapper.selectAllConflictCourseStrategy();
        if(conflictCourseStrategyList!=null && !conflictCourseStrategyList.isEmpty()) {
            for (ConflictCourseStrategy conflictCourseStrategy : conflictCourseStrategyList) {
                if (conflictCourseStrategy.getId() > maxId) {
                    maxId = conflictCourseStrategy.getId();
                }
            }
        }
        return maxId + 1;
    }

    /**
     * 添加冲突课程策略列表
     * @param conflictCourseStrategyList
     * @return 新添加的冲突课程策略的id
     */
    public List<ConflictCourseStrategy> addConflictCourseStrategyList(List<ConflictCourseStrategy> conflictCourseStrategyList){
        Long conflictCourseStrategyId = calculateId();
        for (ConflictCourseStrategy conflictCourseStrategy:conflictCourseStrategyList) {
            conflictCourseStrategy.setId(conflictCourseStrategyId);
            conflictCourseStrategyMapper.addConflictCourseStrategy(conflictCourseStrategy);
        }
        return conflictCourseStrategyMapper.selectConflictCourseStrategyById(conflictCourseStrategyId);
    }


}
