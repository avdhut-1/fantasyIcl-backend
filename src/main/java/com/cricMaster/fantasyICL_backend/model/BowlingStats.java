package com.cricMaster.fantasyICL_backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bowling_stats")
public class BowlingStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bowling_style")
    private String bowlingStyle;


    private Integer matches;
    private Integer innings;


    @Column(precision = 7, scale = 1)
    private BigDecimal overs;

    private Integer balls;

    @Column(name = "runs_conceded")
    private Integer runsConceded;

    private Integer wickets;

    @Column(name = "highest_wickets")
    private Integer highestWickets;

    private Double average;
    private Double economy;

    @Column(name = "strike_rate")
    private Double strikeRate;

    @OneToOne
    @JoinColumn(name = "player_id", nullable = false, unique = true)
    private Player player;

    // constructors, getters & setters

    public BowlingStats() {
    }

    public Long getId() { return id; }
    public String getBowlingStyle() { return bowlingStyle; }
    public void setBowlingStyle(String bowlingStyle) { this.bowlingStyle = bowlingStyle; }

    public Integer getMatches() { return matches; }
    public void setMatches(Integer matches) { this.matches = matches; }

    public Integer getInnings() { return innings; }
    public void setInnings(Integer innings) { this.innings = innings; }

    public BigDecimal getOvers() { return overs; }
    public void setOvers(BigDecimal overs) { this.overs = overs; }

    public Integer getBalls() { return balls; }
    public void setBalls(Integer balls) { this.balls = balls; }

    public Integer getRunsConceded() { return runsConceded; }
    public void setRunsConceded(Integer runsConceded) { this.runsConceded = runsConceded; }

    public Integer getWickets() { return wickets; }
    public void setWickets(Integer wickets) { this.wickets = wickets; }

    public Integer getHighestWickets() { return highestWickets; }
    public void setHighestWickets(Integer highestWickets) { this.highestWickets = highestWickets; }

    public Double getAverage() { return average; }
    public void setAverage(Double average) { this.average = average; }

    public Double getEconomy() { return economy; }
    public void setEconomy(Double economy) { this.economy = economy; }

    public Double getStrikeRate() { return strikeRate; }
    public void setStrikeRate(Double strikeRate) { this.strikeRate = strikeRate; }

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }

}
