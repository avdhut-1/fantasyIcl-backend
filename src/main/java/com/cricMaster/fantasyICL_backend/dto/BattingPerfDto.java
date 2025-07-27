package com.cricMaster.fantasyICL_backend.dto;

public class BattingPerfDto {
    private String playerName;
    private long runs;
    private long balls;
    private int fours;
    private int sixes;
    private double strikeRate;

    public BattingPerfDto() {}

    public BattingPerfDto(String playerName, long runs, long balls, int fours, int sixes, double strikeRate) {
        this.playerName = playerName;
        this.runs = runs;
        this.balls = balls;
        this.fours = fours;
        this.sixes = sixes;
        this.strikeRate = strikeRate;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public long getRuns() {
        return runs;
    }

    public void setRuns(long runs) {
        this.runs = runs;
    }

    public long getBalls() {
        return balls;
    }

    public void setBalls(long balls) {
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

    public double getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(double strikeRate) {
        this.strikeRate = strikeRate;
    }
}
