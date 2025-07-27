package com.cricMaster.fantasyICL_backend.dto;

import java.util.List;

public class ScorecardRequest {
    private SummaryDto summary;
    private List<InningsDto> innings;

    public ScorecardRequest() {}

    public ScorecardRequest(SummaryDto summary, List<InningsDto> innings) {
        this.summary = summary;
        this.innings = innings;
    }

    public SummaryDto getSummary() {
        return summary;
    }

    public void setSummary(SummaryDto summary) {
        this.summary = summary;
    }

    public List<InningsDto> getInnings() {
        return innings;
    }

    public void setInnings(List<InningsDto> innings) {
        this.innings = innings;
    }
}
