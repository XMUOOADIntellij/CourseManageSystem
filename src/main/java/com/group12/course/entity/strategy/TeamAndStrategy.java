package com.group12.course.entity.strategy;

public class TeamAndStrategy extends Strategy{

    private String strategyFirstName;

    private Strategy strategyFirst;

    private String strategySecondName;

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

    public String getStrategySecondName() {
        return strategySecondName;
    }

    public void setStrategySecondName(String strategySecondName) {
        this.strategySecondName = strategySecondName;
    }

    public Strategy getStrategySecond() {
        return strategySecond;
    }

    public void setStrategySecond(Strategy strategySecond) {
        this.strategySecond = strategySecond;
    }
}
