package com.cricMaster.fantasyICL_backend.etl.pdf.model;

import java.math.BigDecimal;

public class BattingPerf {
    public int inningNo;
    public int position;
    public String playerName;
    public String dismissal;
    public int runs, balls, fours, sixes;
    public BigDecimal strikeRate;

    public BattingPerf() {}

    public BattingPerf(int inningNo, String playerName, int position, String dismissal, int runs, int balls, int fours, int sixes, BigDecimal strikeRate) {
        this.inningNo = inningNo;
        this.playerName = playerName;
        this.position = position;
        this.dismissal = dismissal;
        this.runs = runs;
        this.balls = balls;
        this.fours = fours;
        this.sixes = sixes;
        this.strikeRate = strikeRate;
    }

    public int getInningNo() {
        return inningNo;
    }

    public void setInningNo(int inningNo) {
        this.inningNo = inningNo;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getDismissal() {
        return dismissal;
    }

    public void setDismissal(String dismissal) {
        this.dismissal = dismissal;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        this.balls = balls;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public BigDecimal getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(BigDecimal strikeRate) {
        this.strikeRate = strikeRate;
    }
}
