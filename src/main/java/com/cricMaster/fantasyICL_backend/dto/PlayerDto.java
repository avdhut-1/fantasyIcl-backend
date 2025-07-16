package com.cricMaster.fantasyICL_backend.dto;


import java.math.BigDecimal;

/**
 * A flattened DTO for either batting‐ or bowling‐leaderboard rows.
 * Unused fields will simply be null.
 */
public class PlayerDto {
    private Long id;
    private String name;
    private BigDecimal creditScore;

    // stats
    private Integer matches;
    private Integer runs;      // batting only
    private Integer wickets;   // bowling only
    private Double average;    // batting avg or bowling avg
    private Double economy;    // bowling only
    private Double strikeRate;

    public PlayerDto() {}

    public PlayerDto(
            Long id,
            String name,
            BigDecimal creditScore,
            Integer matches,
            Integer runs,
            Integer wickets,
            Double average,
            Double economy,
            Double strikeRate
    ) {
        this.id          = id;
        this.name        = name;
        this.creditScore = creditScore;
        this.matches     = matches;
        this.runs        = runs;
        this.wickets     = wickets;
        this.average     = average;
        this.economy     = economy;
        this.strikeRate  = strikeRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMatches() {
        return matches;
    }

    public void setMatches(Integer matches) {
        this.matches = matches;
    }

    public Integer getRuns() {
        return runs;
    }

    public void setRuns(Integer runs) {
        this.runs = runs;
    }

    public Integer getWickets() {
        return wickets;
    }

    public void setWickets(Integer wickets) {
        this.wickets = wickets;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getEconomy() {
        return economy;
    }

    public void setEconomy(Double economy) {
        this.economy = economy;
    }

    public Double getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(Double strikeRate) {
        this.strikeRate = strikeRate;
    }

    public BigDecimal getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(BigDecimal creditScore) {
        this.creditScore = creditScore;
    }
}
