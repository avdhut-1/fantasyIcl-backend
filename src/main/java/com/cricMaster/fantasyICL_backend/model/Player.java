package com.cricMaster.fantasyICL_backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="credit_score",
            nullable=false,
            precision=3,
            scale=1,
            columnDefinition="NUMERIC(3,1)")
    private BigDecimal creditScore;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private BattingStats battingStats;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private BowlingStats bowlingStats;

    // constructors, getters & setters

    public Player() {}

    public Player(Long id, String name, BigDecimal creditScore, BattingStats battingStats, BowlingStats bowlingStats) {
        this.id = id;
        this.name = name;
        this.creditScore = creditScore;
        this.battingStats = battingStats;
        this.bowlingStats = bowlingStats;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(BigDecimal creditScore) {
        this.creditScore = creditScore;
    }

    public BattingStats getBattingStats() { return battingStats; }
    public void setBattingStats(BattingStats battingStats) {
        if (battingStats == null) {
            if (this.battingStats != null) {
                this.battingStats.setPlayer(null);
            }
        } else {
            battingStats.setPlayer(this);
        }
        this.battingStats = battingStats;
    }

    public BowlingStats getBowlingStats() { return bowlingStats; }
    public void setBowlingStats(BowlingStats bowlingStats) {
        if (bowlingStats == null) {
            if (this.bowlingStats != null) {
                this.bowlingStats.setPlayer(null);
            }
        } else {
            bowlingStats.setPlayer(this);
        }
        this.bowlingStats = bowlingStats;
    }
}