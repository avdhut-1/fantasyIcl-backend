package com.cricMaster.fantasyICL_backend.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contest_innings",
        uniqueConstraints = @UniqueConstraint(columnNames = {"contest_id","innings_number"}))
@EntityListeners(AuditingEntityListener.class)
public class Innings {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contest_id", nullable = false)
    private Scorecard scorecard;

    @Column(name = "innings_number", nullable = false)
    private int inningsNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "batting_team_id", nullable = false)
    private Team battingTeam;

    @Column(name = "captain_name", nullable = false, length = 100)
    private String captainName;

    @CreatedDate
    @Column(name="created_at", nullable=false, updatable=false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name="updated_at", nullable=false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "innings", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BattingPerformance> batting = new ArrayList<>();

    @OneToMany(mappedBy = "innings", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BowlingPerformance> bowling = new ArrayList<>();

    public Innings() {}

    public Innings(Long id, Scorecard scorecard, int inningsNumber, Team battingTeam, String captainName, List<BattingPerformance> batting, List<BowlingPerformance> bowling) {
        this.id = id;
        this.scorecard = scorecard;
        this.inningsNumber = inningsNumber;
        this.battingTeam = battingTeam;
        this.captainName = captainName;
        this.batting = batting;
        this.bowling = bowling;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Scorecard getScorecard() {
        return scorecard;
    }

    public void setScorecard(Scorecard scorecard) {
        this.scorecard = scorecard;
    }

    public int getInningsNumber() {
        return inningsNumber;
    }

    public void setInningsNumber(int inningsNumber) {
        this.inningsNumber = inningsNumber;
    }

    public Team getBattingTeam() {
        return battingTeam;
    }

    public void setBattingTeam(Team battingTeam) {
        this.battingTeam = battingTeam;
    }

    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    public List<BattingPerformance> getBatting() {
        return batting;
    }

    public void setBatting(List<BattingPerformance> batting) {
        this.batting = batting;
    }

    public List<BowlingPerformance> getBowling() {
        return bowling;
    }

    public void setBowling(List<BowlingPerformance> bowling) {
        this.bowling = bowling;
    }
}
