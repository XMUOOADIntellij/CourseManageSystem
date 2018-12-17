package com.group12.course.entity;

import org.springframework.stereotype.Component;

/**
 * KlassRound 实体对象
 * @author Tan Xue
 * @date 2018/12/17
 */

@Component
public class KlassRound {

    private Klass klass;

    private Round round;

    private Integer enrollNumber;

    public Klass getKlass() {
        return klass;
    }

    public void setKlass(Klass klass) {
        this.klass = klass;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public Integer getEnrollNumber() {
        return enrollNumber;
    }

    public void setEnrollNumber(Integer enrollNumber) {
        this.enrollNumber = enrollNumber;
    }
}
