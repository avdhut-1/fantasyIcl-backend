package com.cricMaster.fantasyICL_backend.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contest_scorecards")
@EntityListeners(AuditingEntityListener.class)
public class Scorecard {
    @Id @Column(name="contest_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contest_id")
    private Contest contest;

    @Column(nullable = false)
    private String toss;

    @Column(name = "score_summary", columnDefinition = "TEXT", nullable = false)
    private String scoreSummary;

    @Column(nullable = false)
    private String result;

    @CreatedDate
    @Column(name="created_at", nullable=false, updatable=false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name="updated_at", nullable=false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "scorecard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Innings> innings = new ArrayList<>();

    public Scorecard() {}

    public Scorecard(Long id, Contest contest, String toss, String scoreSummary, String result, Instant createdAt, List<Innings> innings) {
        this.id = id;
        this.contest = contest;
        this.toss = toss;
        this.scoreSummary = scoreSummary;
        this.result = result;
        this.createdAt = createdAt;
        this.innings = innings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public String getToss() {
        return toss;
    }

    public void setToss(String toss) {
        this.toss = toss;
    }

    public String getScoreSummary() {
        return scoreSummary;
    }

    public void setScoreSummary(String scoreSummary) {
        this.scoreSummary = scoreSummary;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public List<Innings> getInnings() {
        return innings;
    }

    public void setInnings(List<Innings> innings) {
        this.innings = innings;
    }
}
