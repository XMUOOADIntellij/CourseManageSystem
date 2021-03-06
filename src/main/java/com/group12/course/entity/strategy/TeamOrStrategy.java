package com.group12.course.entity.strategy;

import java.io.Serializable;
import java.util.List;

/**
 * @author Tan Xue
 */
public class TeamOrStrategy extends Strategy  {

    private String strategyName;

    private List<Strategy> strategyList;

    public TeamOrStrategy() {
        this.strategyType = "TeamOrStrategy";
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
        return "TeamOrStrategy{" +
                "strategyName='" + strategyName + '\'' +
                ", strategyList=" + strategyList +
                ", id=" + id +
                ", strategyType='" + strategyType + '\'' +
                '}';
    }
}
