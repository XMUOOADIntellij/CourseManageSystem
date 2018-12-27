package com.group12.course.entity.strategy;

import java.util.List;

public class TeamAndStrategy extends Strategy{

    private String strategyName;

    private List<Strategy> strategyList;

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public List<Strategy> getStrategyList() {
        return strategyList;
    }

    public void setStrategyList(List<Strategy> strategyList) {
        this.strategyList = strategyList;
    }

    @Override
    public String toString() {
        return "TeamAndStrategy{" +
                "strategyName='" + strategyName + '\'' +
                ", strategyList=" + strategyList +
                '}';
    }
}
