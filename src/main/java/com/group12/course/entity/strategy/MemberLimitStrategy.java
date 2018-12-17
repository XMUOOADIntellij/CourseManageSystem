package com.group12.course.entity.strategy;

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
}
