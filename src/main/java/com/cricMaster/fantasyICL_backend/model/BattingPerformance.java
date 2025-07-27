package com.cricMaster.fantasyICL_backend.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="contest_batting_performances",
        uniqueConstraints=@UniqueConstraint(columnNames={"innings_id","player_name"}))
@EntityListeners(AuditingEntityListener.class)
public class BattingPerformance {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name="innings_id", nullable=false)
    private Innings innings;

    @ManyToOne(optional=false)
    @JoinColumn(name="player_id", nullable=false)
    private Player player;

    @Column(nullable=false)
    private long runs;

    @Column(name = "balls_faced", nullable=false)
    private long balls;

    @Column(nullable=false)
    private int fours;

    @Column(nullable=false)
    private int sixes;

    @Column(name="strike_rate", nullable=false)
    private double strikeRate;

    public BattingPerformance() {}

    public BattingPerformance(Long id, Innings innings, Player player, long runs, long balls, int fours, int sixes, double strikeRate) {
        this.id = id;
        this.innings = innings;
        this.player = player;
        this.runs = runs;
        this.balls = balls;
        this.fours = fours;
        this.sixes = sixes;
        this.strikeRate = strikeRate;
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

    public long getRuns() {
        return runs;
    }

    public void setRuns(long runs) {
        this.runs = runs;
    }

    public long getBalls() {
        return balls;
    }

    public void setBalls(long balls) {
        this.balls = balls;
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

    public double getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(double strikeRate) {
        this.strikeRate = strikeRate;
    }
}
