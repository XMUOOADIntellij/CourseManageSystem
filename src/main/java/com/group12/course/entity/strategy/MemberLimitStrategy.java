package com.group12.course.entity.strategy;

import com.group12.course.entity.Student;
import com.group12.course.entity.Team;

import java.io.Serializable;
import java.util.List;

/**
 * 总人数限制策略
 *
 * @author Xu Gang
 * @date 2018年12月17日
 */
public class MemberLimitStrategy extends Strategy implements Serializable {

    private Integer minMember;

    private Integer maxMember;

    public MemberLimitStrategy() {
        this.strategyType="MemberLimitStrategy";
    }

    public MemberLimitStrategy(Long id, Integer minMember, Integer maxMember) {
        super(id);
        this.minMember = minMember;
        this.maxMember = maxMember;
    }

    @Override
    public String toString() {
        return "MemberLimitStrategy{" +
                "minMember=" + minMember +
                ", maxMember=" + maxMember +
                ", id=" + id +
                ", strategyType='" + strategyType + '\'' +
                '}';
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

}
