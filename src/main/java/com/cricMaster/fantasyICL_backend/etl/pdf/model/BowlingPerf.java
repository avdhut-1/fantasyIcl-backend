package com.cricMaster.fantasyICL_backend.etl.pdf.model;

import java.math.BigDecimal;

public class BowlingPerf {
    public int inningNo;
    public int position;
    public String playerName;
    public BigDecimal overs;
    public int maidens, runsConceded, wickets;
    public int zeros, fours, sixes, wides, noballs;
    public BigDecimal economy;

    public BowlingPerf() {}

    public BowlingPerf(int inningNo, int position, String playerName, BigDecimal overs, int maidens, int runsConceded, int wickets, int zeros, int fours, int sixes, int wides, int noballs, BigDecimal economy) {
        this.inningNo = inningNo;
        this.position = position;
        this.playerName = playerName;
        this.overs = overs;
        this.maidens = maidens;
        this.runsConceded = runsConceded;
        this.wickets = wickets;
        this.zeros = zeros;
        this.fours = fours;
        this.sixes = sixes;
        this.wides = wides;
        this.noballs = noballs;
        this.economy = economy;
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

    public BigDecimal getOvers() {
        return overs;
    }

    public void setOvers(BigDecimal overs) {
        this.overs = overs;
    }

    public int getMaidens() {
        return maidens;
    }

    public void setMaidens(int maidens) {
        this.maidens = maidens;
    }

    public int getRunsConceded() {
        return runsConceded;
    }

    public void setRunsConceded(int runsConceded) {
        this.runsConceded = runsConceded;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public int getZeros() {
        return zeros;
    }

    public void setZeros(int zeros) {
        this.zeros = zeros;
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

    public int getWides() {
        return wides;
    }

    public void setWides(int wides) {
        this.wides = wides;
    }

    public int getNoballs() {
        return noballs;
    }

    public void setNoballs(int noballs) {
        this.noballs = noballs;
    }

    public BigDecimal getEconomy() {
        return economy;
    }

    public void setEconomy(BigDecimal economy) {
        this.economy = economy;
    }
}
