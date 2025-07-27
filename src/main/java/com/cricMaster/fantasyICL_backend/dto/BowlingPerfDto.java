package com.cricMaster.fantasyICL_backend.dto;

public class BowlingPerfDto {
    private String playerName;
    private double overs;
    private long runsConceded;
    private int wickets;
    private double economy;

    public BowlingPerfDto() {}

    public BowlingPerfDto(String playerName, double overs, long runsConceded, int wickets, double economy) {
        this.playerName = playerName;
        this.overs = overs;
        this.runsConceded = runsConceded;
        this.wickets = wickets;
        this.economy = economy;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public double getOvers() {
        return overs;
    }

    public void setOvers(double overs) {
        this.overs = overs;
    }

    public long getRunsConceded() {
        return runsConceded;
    }

    public void setRunsConceded(long runsConceded) {
        this.runsConceded = runsConceded;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public double getEconomy() {
        return economy;
    }

    public void setEconomy(double economy) {
        this.economy = economy;
    }
}
