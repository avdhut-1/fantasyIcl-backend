package com.cricMaster.fantasyICL_backend.dto;

public class SummaryDto {
    private String match;
    private String ground;
    private String date;
    private String toss;
    private String scoreSummary;
    private String result;

    public SummaryDto() {}

    public SummaryDto(String match, String ground, String date, String toss, String scoreSummary, String result) {
        this.match = match;
        this.ground = ground;
        this.date = date;
        this.toss = toss;
        this.scoreSummary = scoreSummary;
        this.result = result;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getGround() {
        return ground;
    }

    public void setGround(String ground) {
        this.ground = ground;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getToss() {
        return toss;
    }

    public void setToss(String toss) {
        this.toss = toss;
    }

    public String getScoreSummary() {
        return scoreSummary;
    }

    public void setScoreSummary(String scoreSummary) {
        this.scoreSummary = scoreSummary;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
