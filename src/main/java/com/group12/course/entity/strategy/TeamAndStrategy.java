package com.group12.course.entity.strategy;

public class TeamAndStrategy extends Strategy{

    private String strategyName;

    private Strategy strategy;

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
