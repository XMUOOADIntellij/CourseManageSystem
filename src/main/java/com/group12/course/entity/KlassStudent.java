package com.group12.course.entity;
import java.io.Serializable;

/**
 * @author Tan Xue
 */

public class KlassStudent implements Serializable {

    private Klass klass;

    private Student student;

    private Course course;

    private Team team;

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "KlassStudent{" +
                "klass=" + klass +
                ", student=" + student +
                ", course=" + course +
                ", team=" + team +
                '}';
    }
}
