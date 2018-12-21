package com.group12.course.controller.vo;

import com.group12.course.entity.RoundScore;
import java.math.BigDecimal;


public class ScoreVO {

    private Long courseId;

    private Long roundId;
    private Integer roundSerial;
    private RoundPart roundPart;

    public class RoundPart{
    private Long teamId;
    private Integer teamSerial;
    private Integer classSerial;

    private BigDecimal totalScore;
    private BigDecimal presentationScore;
    private BigDecimal questionScore;
    private BigDecimal reportScore;

        public Long getTeamId() {
            return teamId;
        }

        public void setTeamId(Long teamId) {
            this.teamId = teamId;
        }

        public Integer getTeamSerial() {
            return teamSerial;
        }

        public void setTeamSerial(Integer teamSerial) {
            this.teamSerial = teamSerial;
        }

        public Integer getClassSerial() {
            return classSerial;
        }

        public void setClassSerial(Integer classSerial) {
            this.classSerial = classSerial;
        }

        public BigDecimal getTotalScore() {
            return totalScore;
        }

        public void setTotalScore(BigDecimal totalScore) {
            this.totalScore = totalScore;
        }

        public BigDecimal getPresentationScore() {
            return presentationScore;
        }

        public void setPresentationScore(BigDecimal presentationScore) {
            this.presentationScore = presentationScore;
        }

        public BigDecimal getQuestionScore() {
            return questionScore;
        }

        public void setQuestionScore(BigDecimal questionScore) {
            this.questionScore = questionScore;
        }

        public BigDecimal getReportScore() {
            return reportScore;
        }

        public void setReportScore(BigDecimal reportScore) {
            this.reportScore = reportScore;
        }
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    public Integer getRoundSerial() {
        return roundSerial;
    }

    public void setRoundSerial(Integer roundSerial) {
        this.roundSerial = roundSerial;
    }

    public RoundPart getRoundPart() {
        return roundPart;
    }

    public void setRoundPart(RoundPart roundPart) {
        this.roundPart = roundPart;
    }

    public ScoreVO(){};

    public ScoreVO(RoundScore roundScore){
        courseId = roundScore.getRound().getCourse().getId();
        roundId = roundScore.getRound().getId();
        roundSerial = roundScore.getRound().getRoundSerial();
        roundPart = new RoundPart();
        //TODO 队伍课程找到班级 roundPart.setClassSerial(roundScore.getTeam().);
        roundPart.setPresentationScore(roundScore.getPresentationScore());
        roundPart.setQuestionScore(roundScore.getQuestionScore());
        roundPart.setReportScore(roundScore.getReportScore());
        roundPart.setTotalScore(roundScore.getTotalScore());
        roundPart.setTeamId(roundScore.getTeam().getId());
        roundPart.setTeamSerial(roundScore.getTeam().getTeamSerial());
    }
}
