package com.cricMaster.fantasyICL_backend.etl.pdf.model;

import java.math.BigDecimal;

public class InningsDetail {
    public int inningNo;
    public long battingTeamId;
    public int totalRuns, totalWkts;
    public BigDecimal totalOvers;
    public String extras;

    public InningsDetail() {}

    public InningsDetail(int inningNo, long battingTeamId, int totalRuns, int totalWkts, BigDecimal totalOvers, String extras) {
        this.inningNo = inningNo;
        this.battingTeamId = battingTeamId;
        this.totalRuns = totalRuns;
        this.totalWkts = totalWkts;
        this.totalOvers = totalOvers;
        this.extras = extras;
    }

    public int getInningNo() {
        return inningNo;
    }

    public void setInningNo(int inningNo) {
        this.inningNo = inningNo;
    }

    public long getBattingTeamId() {
        return battingTeamId;
    }

    public void setBattingTeamId(long battingTeamId) {
        this.battingTeamId = battingTeamId;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }

    public int getTotalWkts() {
        return totalWkts;
    }

    public void setTotalWkts(int totalWkts) {
        this.totalWkts = totalWkts;
    }

    public BigDecimal getTotalOvers() {
        return totalOvers;
    }

    public void setTotalOvers(BigDecimal totalOvers) {
        this.totalOvers = totalOvers;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }
}
