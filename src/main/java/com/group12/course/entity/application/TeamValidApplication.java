package com.group12.course.entity.application;

import com.group12.course.entity.Teacher;
import com.group12.course.entity.Team;
import org.springframework.stereotype.Component;

/**
 * 队伍申请对象
 * @author Xu Gang
 * @date 2018年12月17日
 */
@Component
public class TeamValidApplication extends Application {

    private Team team;

    private Teacher teacher;

    private String reason;

    public TeamValidApplication() {
    }

    public TeamValidApplication(Integer status, Team team, Teacher teacher, String reason) {
        super(status);
        this.team = team;
        this.teacher = teacher;
        this.reason = reason;
    }

    public TeamValidApplication(Integer id, Integer status, Team team, Teacher teacher, String reason) {
        super(id, status);
        this.team = team;
        this.teacher = teacher;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "TeamValidApplication{" +
                "team=" + team +
                ", teacher=" + teacher +
                ", reason='" + reason + '\'' +
                '}';
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
