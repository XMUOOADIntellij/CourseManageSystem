package com.group12.course.entity.strategy;

import com.group12.course.entity.Student;
import com.group12.course.entity.Team;

import java.util.List;

/**
 * 总人数限制策略
 *
 * @author Xu Gang
 * @date 2018年12月17日
 */
public class MemberLimitStrategy extends Strategy {

    private Integer minMember;

    private Integer maxMember;

    public MemberLimitStrategy() {
    }

    public MemberLimitStrategy(Long id, Integer minMember, Integer maxMember) {
        super(id);
        this.minMember = minMember;
        this.maxMember = maxMember;
    }

    public Integer getMinMember() {
        return minMember;
    }

    public void setMinMember(Integer minMember) {
        this.minMember = minMember;
    }

    public Integer getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(Integer maxMember) {
        this.maxMember = maxMember;
    }

    @Override
    public Boolean judgeTeam(Team team){
        int memberCount = 0;
        Student leader = team.getLeader();
        List<Student> member = team.getMembers();
        if (member==null||member.isEmpty()){
            memberCount=1;
        }
        else {
            memberCount=member.size()+1;
        }
        if (memberCount<minMember){
            return false;
        }
        else if (memberCount>maxMember){
            return false;
        }
        else {
            return true;
        }
    }
}
