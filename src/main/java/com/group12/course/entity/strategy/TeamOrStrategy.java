package com.group12.course.entity.strategy;

public class TeamOrStrategy extends Strategy{

    private String strategyName;

    private Strategy strategyt;

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public Strategy getStrategyt() {
        return strategyt;
    }

    public void setStrategyt(Strategy strategyt) {
        this.strategyt = strategyt;
    }
}
