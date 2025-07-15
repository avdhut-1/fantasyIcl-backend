package com.cricMaster.fantasyICL_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "batting_stats")
public class BattingStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "batting_hand")
    private String battingHand;

    @Column(name = "matches")
    private Integer matches;

    @Column(name = "innings")
    private Integer innings;

    @Column(name = "runs")
    private Integer runs;

    @Column(name = "balls")
    private Integer balls;

    @Column(name = "average")
    private Double average;


    @Column(name = "strike_rate")
    private Double strikeRate;

    @Column(name = "fifties")
    private Integer fifties;

    @Column(name = "hundreds")
    private Integer hundreds;

    @OneToOne
    @JoinColumn(name = "player_id", nullable = false, unique = true)
    private Player player;

    public BattingStats() {}

    public String getBattingHand() { return battingHand; }
    public void setBattingHand(String battingHand) { this.battingHand = battingHand; }

    public Integer getMatches() { return matches; }
    public void setMatches(Integer matches) { this.matches = matches; }

    public Integer getInnings() { return innings; }
    public void setInnings(Integer innings) { this.innings = innings; }

    public Integer getRuns() { return runs; }
    public void setRuns(Integer runs) { this.runs = runs; }

    public Integer getBalls() { return balls; }
    public void setBalls(Integer balls) { this.balls = balls; }

    public Double getAverage() { return average; }
    public void setAverage(Double average) { this.average = average; }

    public Double getStrikeRate() { return strikeRate; }
    public void setStrikeRate(Double strikeRate) { this.strikeRate = strikeRate; }

    public Integer getFifties() { return fifties; }
    public void setFifties(Integer fifties) { this.fifties = fifties; }

    public Integer getHundreds() { return hundreds; }
    public void setHundreds(Integer hundreds) { this.hundreds = hundreds; }

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }
}
