package com.cricMaster.fantasyICL_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String playerName;

    @Column(name = "credit_score", nullable = false)
    private String creditScore;


    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private BattingStats battingStats;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private BowlingStats bowlingStats;

    // constructors, getters & setters

    public Player() {}

    public Player(Long id, String playerName, String creditScore, BattingStats battingStats, BowlingStats bowlingStats) {
        this.id = id;
        this.playerName = playerName;
        this.creditScore = creditScore;
        this.battingStats = battingStats;
        this.bowlingStats = bowlingStats;
    }

    public Long getId() { return id; }
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }


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