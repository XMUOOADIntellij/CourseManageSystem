package com.group12.course.entity.strategy;

import java.io.Serializable;
import java.util.List;

/**
 * @author Tan Xue
 */
public class TeamAndStrategy extends Strategy implements Serializable {

    private String strategyName;

    private List<Strategy> strategyList;

    public TeamAndStrategy() {
        this.strategyType="TeamAndStrategy";
    }

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
                ", strategyType='" + strategyType + '\'' +
                '}';
    }
}
