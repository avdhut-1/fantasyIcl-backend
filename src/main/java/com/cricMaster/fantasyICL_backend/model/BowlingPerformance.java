package com.cricMaster.fantasyICL_backend.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="contest_bowling_performances",
        uniqueConstraints=@UniqueConstraint(columnNames={"innings_id","player_name"}))
@EntityListeners(AuditingEntityListener.class)
public class BowlingPerformance {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="innings_id", nullable=false)
    private Innings innings;

    @ManyToOne(optional=false)
    @JoinColumn(name="player_id", nullable=false)
    private Player player;

    @Column(name = "overs_bowled", nullable=false)
    private double overs;

    @Column(name="runs_conceded", nullable=false)
    private long runsConceded;

    @Column(nullable=false)
    private int wickets;

    @Column(name = "economy_rate", nullable=false)
    private double economy;

    public BowlingPerformance() {}

    public BowlingPerformance(Long id, Innings innings, Player player, double overs, long runsConceded, int wickets, double economy) {
        this.id = id;
        this.innings = innings;
        this.player = player;
        this.overs = overs;
        this.runsConceded = runsConceded;
        this.wickets = wickets;
        this.economy = economy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Innings getInnings() {
        return innings;
    }

    public void setInnings(Innings innings) {
        this.innings = innings;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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
