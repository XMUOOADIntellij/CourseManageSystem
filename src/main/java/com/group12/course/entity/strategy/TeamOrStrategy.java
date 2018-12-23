package com.group12.course.entity.strategy;

public class TeamOrStrategy extends Strategy{

    private String strategyFirstName;

    private Strategy strategyFirst;

    private String getStrategySecondName;

    private Strategy strategySecond;

    public String getStrategyFirstName() {
        return strategyFirstName;
    }

    public void setStrategyFirstName(String strategyFirstName) {
        this.strategyFirstName = strategyFirstName;
    }

    public Strategy getStrategyFirst() {
        return strategyFirst;
    }

    public void setStrategyFirst(Strategy strategyFirst) {
        this.strategyFirst = strategyFirst;
    }

    public String getGetStrategySecondName() {
        return getStrategySecondName;
    }

    public void setGetStrategySecondName(String getStrategySecondName) {
        this.getStrategySecondName = getStrategySecondName;
    }

    public Strategy getStrategySecond() {
        return strategySecond;
    }

    public void setStrategySecond(Strategy strategySecond) {
        this.strategySecond = strategySecond;
    }
}
