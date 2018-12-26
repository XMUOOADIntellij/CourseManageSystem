package com.group12.course.entity.strategy;

import com.group12.course.entity.Team;

/**
 * 策略类，无方法，仅供各详细策略继承
 *
 * @author Xu Gang
 * @date 2018年12月17日
 */
public class Strategy {
    Long id;

    public Strategy() {
    }

    public Strategy(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean judgeTeam(Team team){
        return true;
    }
}
