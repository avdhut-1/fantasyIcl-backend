package com.cricMaster.fantasyICL_backend.dto;

import java.util.List;

public class InningsDto {
    private int inningsNumber;
    private long battingTeamId;
    private String captainName;
    private List<BattingPerfDto> batting;
    private List<BowlingPerfDto> bowling;

    public InningsDto() {}

    public InningsDto(int inningsNumber, long battingTeamId, String captainName, List<BattingPerfDto> batting, List<BowlingPerfDto> bowling) {
        this.inningsNumber = inningsNumber;
        this.battingTeamId = battingTeamId;
        this.captainName = captainName;
        this.batting = batting;
        this.bowling = bowling;
    }

    public int getInningsNumber() {
        return inningsNumber;
    }

    public void setInningsNumber(int inningsNumber) {
        this.inningsNumber = inningsNumber;
    }

    public long getBattingTeamId() {
        return battingTeamId;
    }

    public void setBattingTeamId(long battingTeamId) {
        this.battingTeamId = battingTeamId;
    }

    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    public List<BattingPerfDto> getBatting() {
        return batting;
    }

    public void setBatting(List<BattingPerfDto> batting) {
        this.batting = batting;
    }

    public List<BowlingPerfDto> getBowling() {
        return bowling;
    }

    public void setBowling(List<BowlingPerfDto> bowling) {
        this.bowling = bowling;
    }
}
