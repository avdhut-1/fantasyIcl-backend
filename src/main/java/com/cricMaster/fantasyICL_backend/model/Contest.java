package com.cricMaster.fantasyICL_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Instant;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "contests",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_contest_slot",
                columnNames = {"tournament_id","match_date","match_time"}))
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_contest_tournament"))
    private Tournament tournament;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "team_a_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_contest_team_a"))
    private Team teamA;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "team_b_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_contest_team_b"))
    private Team teamB;

    @Column(name = "match_date", nullable = false)
    private LocalDate matchDate;

    @Column(name = "match_time", nullable = false)
    private LocalTime matchTime;

    @Column(nullable = false, length = 100)
    private String venue;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;


    public Contest(Long id, Tournament tournament, Team teamA, Team teamB, LocalDate matchDate, LocalTime matchTime, String venue, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.tournament = tournament;
        this.teamA = teamA;
        this.teamB = teamB;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
        this.venue = venue;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Contest() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Team getTeamA() {
        return teamA;
    }

    public void setTeamA(Team teamA) {
        this.teamA = teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public void setTeamB(Team teamB) {
        this.teamB = teamB;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public LocalTime getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(LocalTime matchTime) {
        this.matchTime = matchTime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}

